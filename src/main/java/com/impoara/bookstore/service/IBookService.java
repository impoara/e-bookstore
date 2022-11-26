package com.impoara.bookstore.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.impoara.bookstore.domain.Book;

import java.util.List;

public interface IBookService extends IService<Book> {

    Book getById(Integer id);

    List<Book> getBookList();

    IPage<Book> getPage(int currentPage, int pageSize);

    IPage<Book> getPageBy(int currentPage, int pageSize, Book book);

    Book getListByCart(int bid);

    Float getPriceById(Integer id);



}
