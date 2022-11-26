package com.impoara.bookstore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.impoara.bookstore.dao.OrdersDao;
import com.impoara.bookstore.domain.Book;
import com.impoara.bookstore.domain.Orderitem;
import com.impoara.bookstore.domain.Orders;
import com.impoara.bookstore.service.IOrdersService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersDao, Orders> implements IOrdersService {

    final OrdersDao ordersDao;

    OrdersServiceImpl(OrdersDao ordersDao){
        this.ordersDao = ordersDao;
    }

    @Override
    public Orders getById(Integer id) {
        QueryWrapper qw = new QueryWrapper();
        qw.eq("oid",id);
        return ordersDao.selectOne(qw);
    }

    @Override
    public Orders getByDateAndUid(String date, Integer uid) {
        QueryWrapper qw = new QueryWrapper();
        qw.eq("ordertime",date);
        qw.eq("uid",uid);
        List<Orders> orderList = ordersDao.selectList(qw);
        if (orderList.size() > 1) throw new RuntimeException();
        return orderList.get(0);
    }

    @Override
    public boolean setOrdersTotal(Orders orders, List<Orderitem> orderitemList) {
        Float orderMoney = (float) 0;
        for (Orderitem orderitem : orderitemList) {
            orderMoney += orderitem.getSubtotal();
        }
        orders.setTotal(orderMoney);
        QueryWrapper qw = new QueryWrapper();
        qw.eq("oid",orders.getOid());
        ordersDao.update(orders,qw);
        return true;
    }

    @Override
    public boolean setOrdersAddressAndPhone(Orders orders) {
        QueryWrapper qw = new QueryWrapper();
        qw.eq("oid",orders.getOid());
        ordersDao.update(orders,qw);
        return true;
    }

    @Override
    public boolean payToSetOrdersStateTo1(Integer oid, Integer state) {
        QueryWrapper qw = new QueryWrapper();
        qw.eq("oid",oid);
        Orders orders = new Orders();
        orders.setOid(oid);
        orders.setState(state);
        ordersDao.update(orders,qw);
        return true;
    }

    @Override
    public List<Orders> getOrderListByUid(Integer uid) {
        QueryWrapper qw = new QueryWrapper();
        qw.eq("uid",uid);
        return ordersDao.selectList(qw);
    }

    @Override
    public IPage<Orders> getPageByUid(int currentPage, int pageSize, Integer uid) {
        QueryWrapper qw = new QueryWrapper();
        qw.eq("uid",uid);
        IPage page = new Page(currentPage, pageSize);
        ordersDao.selectPage(page,qw);
        //调用方法设置图书属性
        return page;
    }

    @Override
    public IPage<Orders> getPageBy(int currentPage, int pageSize, Integer uid, Orders orders) {
        LambdaQueryWrapper<Orders> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Orders::getUid, uid);
        lqw.eq(orders.getState() != null, Orders::getState, orders.getState());
        lqw.like(Strings.isNotEmpty(orders.getOrdertime()), Orders::getOrdertime, orders.getOrdertime());
        IPage page = new Page(currentPage, pageSize);
        ordersDao.selectPage(page,lqw);
        //调用方法设置图书属性
        return page;
    }


}
