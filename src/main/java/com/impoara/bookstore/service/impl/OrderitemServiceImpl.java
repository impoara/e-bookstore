package com.impoara.bookstore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.impoara.bookstore.dao.OrderItemDao;
import com.impoara.bookstore.domain.Orderitem;
import com.impoara.bookstore.service.IOrderitemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderitemServiceImpl extends ServiceImpl<OrderItemDao, Orderitem> implements IOrderitemService {
    @Autowired
    private OrderItemDao orderItemDao;

    @Override
    public Orderitem getById(Integer id) {
        QueryWrapper qw = new QueryWrapper();
        qw.eq("orid",id);
        return orderItemDao.selectOne(qw);
    }

    @Override
    public List<Orderitem> getListByOid(Integer oid) {
        QueryWrapper qw = new QueryWrapper();
        qw.eq("oid",oid);
        return orderItemDao.selectList(qw);
    }
}
