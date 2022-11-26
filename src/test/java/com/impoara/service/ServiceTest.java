package com.impoara.service;

import com.impoara.bookstore.domain.Book;
import com.impoara.bookstore.domain.User;
import com.impoara.bookstore.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ServiceTest {

    @Autowired
    private IBookService bookService;
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IOrdersService ordersService;
    @Autowired
    private IOrderitemService orderitemService;


    @Test
    void getBookById(){
        System.out.println(bookService.getById(1));
    }

    @Test
    void getCategoryById(){
        System.out.println(categoryService.getById(1));
    }

    @Test
    void getUserById(){
        System.out.println(userService.getById(1));
    }

    @Test
    void getOrdersById(){
        System.out.println(ordersService.getById(1));
    }

    @Test
    void getOrderItemById(){
        System.out.println(orderitemService.getById(1));
    }

    @Test
    void Login(){
        User user = new User();
        user.setUsername("impoara");
        user.setPassword("123456");
        System.out.println(userService.Login(user));
    }

    @Test
    void getBookList(){
        for (Book book : bookService.getBookList()) {
            System.out.println(book);
        }

    }

    @Test
    void getPage(){
        System.out.println(bookService.getPage(1, 5).getRecords());
    }
}
