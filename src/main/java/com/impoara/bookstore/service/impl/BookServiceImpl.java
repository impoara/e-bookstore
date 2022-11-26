package com.impoara.bookstore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.impoara.bookstore.dao.BookDao;
import com.impoara.bookstore.domain.Book;
import com.impoara.bookstore.domain.Category;
import com.impoara.bookstore.service.IBookService;
import com.impoara.bookstore.service.ICategoryService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl extends ServiceImpl<BookDao, Book> implements IBookService {
    @Autowired
    private BookDao bookDao;

    @Autowired
    private ICategoryService categoryService;

    @Override
    public Book getById(Integer id) {
        QueryWrapper qw = new QueryWrapper();
        qw.eq("bid", id);
        return bookDao.selectOne(qw);
    }

    @Override
    public List<Book> getBookList() {
        List<Category> categoryList =  categoryService.list();
        List<Book> bookList = bookDao.selectList(null);
        //将每本书的cid,对应分类中的名字,进行赋值.
        for(Book book : bookList){
            for (Category category : categoryList){
                if (book.getCid().equals(category.getCid())){
                    book.setBtype(category.getCname());
                    break;
                }
            }
        }
        return bookList;
    }

    //设置图书属性方法
    public List<Book> setBookType(List<Book> bookList){
        //类型处理
        List<Category> categoryList =  categoryService.list();
        //将每本书的cid,对应分类中的名字,进行赋值.
        for(Book book : bookList){
            for (Category category : categoryList){
                if (book.getCid().equals(category.getCid())){
                    book.setBtype(category.getCname());
                    break;
                }
            }
        }
        return bookList;
    }

    @Override
    public IPage<Book> getPage(int currentPage, int pageSize) {
        IPage page = new Page(currentPage, pageSize);
        bookDao.selectPage(page,null);
        //调用方法设置图书属性
        page.setRecords(setBookType(page.getRecords()));
        return page;
    }

    @Override
    public IPage<Book> getPageBy(int currentPage, int pageSize, Book book) {
        LambdaQueryWrapper<Book> lqw = new LambdaQueryWrapper<Book>();
        //条件组织
        lqw.like(book.getCid() != null, Book::getCid, book.getCid());
        lqw.like(Strings.isNotEmpty(book.getAuthor()),Book::getAuthor,book.getAuthor());
        lqw.like(Strings.isNotEmpty(book.getBname()),Book::getBname,book.getBname());

        IPage page = new Page(currentPage, pageSize);
        bookDao.selectPage(page,lqw);
        //调用方法设置图书属性
        page.setRecords(setBookType(page.getRecords()));
        return page;
    }

    @Override
    public Book getListByCart(int bid) {
        QueryWrapper<Book> qw = new QueryWrapper<>();
        qw.eq("bid",bid);
        return bookDao.selectOne(qw);
    }

    @Override
    public Float getPriceById(Integer id) {
        Book book = bookDao.selectById(id);
        return book.getPrice();
    }
}
