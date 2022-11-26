package com.impoara.bookstore.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.impoara.bookstore.domain.Book;
import com.impoara.bookstore.domain.Orderitem;
import com.impoara.bookstore.domain.Orders;

import java.util.List;

public interface IOrdersService extends IService<Orders> {
    Orders getById(Integer id);

    Orders getByDateAndUid(String date,Integer uid);

    boolean setOrdersTotal(Orders orders, List<Orderitem> orderitemList);

    boolean setOrdersAddressAndPhone(Orders orders);

    boolean payToSetOrdersStateTo1(Integer oid, Integer state);

    List<Orders> getOrderListByUid(Integer uid);

    IPage<Orders> getPageByUid(int currentPage, int pageSize, Integer uid);

    IPage<Orders> getPageBy(int currentPage, int pageSize, Integer uid, Orders orders);

}
