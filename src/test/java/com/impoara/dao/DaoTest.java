package com.impoara.dao;

import com.impoara.bookstore.dao.*;
import com.impoara.bookstore.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class DaoTest {
    @Autowired
    private BookDao bookDao;
    @Autowired
    private CategoryDao categoryDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private OrdersDao ordersDao;
    @Autowired
    private OrderItemDao orderItemDao;


    @Test
    void TestBookList(){
        for (Book book : bookDao.selectList(null)) {
            System.out.println(book);
        }
    }

    @Test
    void TestCategory(){
        for (Category category : categoryDao.selectList(null)) {
            System.out.println(category);
        }
    }

    @Test
    void testUser(){
        for (User user : userDao.selectList(null)) {
            System.out.println(user);
        }
    }

    @Test
    void testOrders(){
        for (Orders orders : ordersDao.selectList(null)) {
            System.out.println(orders);
        }
    }

    @Test
    void testOrderItem(){
        List<Orderitem> orderitems = orderItemDao.selectList(null);
        orderitems.forEach(Orderitem -> System.out.println(Orderitem));
    }

}
