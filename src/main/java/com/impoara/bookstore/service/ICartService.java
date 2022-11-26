package com.impoara.bookstore.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.impoara.bookstore.domain.Cart;

import java.util.List;

public interface ICartService extends IService<Cart> {

    boolean addToCart(Cart cart);

    List<Cart> getCartListByUid(Integer uid);

    boolean removeCarts(List<Cart> cartList);
}
