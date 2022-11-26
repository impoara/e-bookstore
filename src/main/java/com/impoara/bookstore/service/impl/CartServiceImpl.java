package com.impoara.bookstore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.impoara.bookstore.dao.CartDao;
import com.impoara.bookstore.domain.Cart;
import com.impoara.bookstore.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl extends ServiceImpl<CartDao, Cart> implements ICartService {

    final CartDao cartDao;

    CartServiceImpl(CartDao cartDao){
        this.cartDao = cartDao;
    }

    @Override
    public boolean addToCart(Cart cart) {
        QueryWrapper<Cart> qw = new QueryWrapper<>();
        qw.eq("uid",cart.getUid())
                .eq("bid",cart.getBid());
        Cart selectOne = cartDao.selectOne(qw);
        if (selectOne != null){
            selectOne.setCount(selectOne.getCount() + cart.getCount());
            cartDao.updateById(selectOne);
        }else {
            cartDao.insert(cart);
        }
        return true;
    }

    @Override
    public List<Cart> getCartListByUid(Integer uid) {
        QueryWrapper<Cart> qw = new QueryWrapper<>();
        qw.eq("uid",uid);
        List<Cart> cartList = cartDao.selectList(qw);
        return cartList;
    }

    @Override
    public boolean removeCarts(List<Cart> cartList) {
        List<Integer> idList = new ArrayList<>();
        for (Cart cart : cartList) {
            idList.add(cart.getCaid());
        }
        cartDao.deleteBatchIds(idList);
        return true;
    }
}
