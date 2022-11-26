package com.impoara.bookstore.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.impoara.bookstore.controller.utils.R;
import com.impoara.bookstore.domain.Book;
import com.impoara.bookstore.service.IBookService;
import com.impoara.bookstore.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/books")
public class BookController {

    @Autowired
    private IBookService bookService;

    @Autowired
    private ICategoryService categoryService;

    //获取图书列表分页
    @GetMapping("{currentPage}/{pageSize}")
    public R getPage(@PathVariable int currentPage, @PathVariable int pageSize, Book book){
        IPage<Book> page = bookService.getPageBy(currentPage,pageSize,book);
        //如果当前页面大于总页码, 那么重新执行查询操作, 使用最大页码值作为当前页码值.(但是我在前端解决了该问题)
        /*if(currentPage > page.getPages()){
            page = bookService.getPage((int)page.getPages(), pageSize);
        }*/
        return new R(true, page);
    }

    @GetMapping("/type")
    public R getBookTypeList(){
        return new R(true,categoryService.list());
    }


}
