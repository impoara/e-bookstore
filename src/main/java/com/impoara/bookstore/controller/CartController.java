package com.impoara.bookstore.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.impoara.bookstore.controller.utils.R;
import com.impoara.bookstore.domain.Book;
import com.impoara.bookstore.domain.Cart;
import com.impoara.bookstore.domain.Orderitem;
import com.impoara.bookstore.domain.Orders;
import com.impoara.bookstore.service.IBookService;
import com.impoara.bookstore.service.ICartService;
import com.impoara.bookstore.service.IOrderitemService;
import com.impoara.bookstore.service.IOrdersService;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/cart")
public class CartController {

    final ICartService cartService;

    final IBookService bookService;

    final IOrderitemService orderitemService;

    final IOrdersService ordersService;

    CartController(ICartService cartService,IBookService bookService,IOrderitemService orderitemService,IOrdersService ordersService){
        this.cartService = cartService;
        this.bookService = bookService;
        this.orderitemService = orderitemService;
        this.ordersService = ordersService;
    }

    @PostMapping("/add")
    public R addToCart(@RequestBody Cart cart){
        boolean flag = false;
        HashMap hm = new HashMap();
        Object data = null;
        if (cart.getUid() != null && cart.getBid() != null && cart.getCount() != null) {
            flag = cartService.addToCart(cart);
        }
        hm.put("flag",flag);
        data = hm;
        System.out.println(cart);
        return new R(true,data);
    }

    @GetMapping("{uid}")
    public R getCartListByUid(@PathVariable Integer uid){
        List<Cart> cartList = cartService.getCartListByUid(uid);
        for (Cart cart : cartList) {
            Book book = bookService.getListByCart(cart.getBid());
            cart.setBook(book);
        }
        HashMap hm = new HashMap();
        hm.put("records",cartList);
        Object data = hm;
        return new R(true,data);
    }

    @PostMapping("/remove")
    public R removeCartById(@RequestBody List<Cart> cartList){
        List<Integer> caIds = new ArrayList<>();
        for (Cart cart : cartList) {
            caIds.add(cart.getCaid());
        }
        boolean flag = cartService.removeByIds(caIds);
        return new R(flag);
    }

    @PostMapping("/update")
    public R updateCartById(@RequestBody List<Cart> cartList){
        boolean flag = cartService.updateBatchById(cartList);
        return new R(flag);
    }

    @PostMapping("/cartToOrder")
    public R makeOrderAndItem(@RequestBody List<Cart> cartList){
        //??????????????????
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy???MM???dd???-HH???mm???ss???SSS??????");//
        String date = sdf.format(new Date());
        //????????????
        Orders orders = new Orders();
        orders.setOrdertime(date);
        orders.setUid(cartList.get(0).getUid());
        ordersService.save(orders);
        //????????????????????????
        //???????????????????????????id
        Orders madeOrders = ordersService.getByDateAndUid(date, cartList.get(0).getUid());
        List<Orderitem> orderitemList = new ArrayList<>();
        //??????????????????
        for (Cart cart : cartList) {
            Orderitem orderitem = new Orderitem();
            orderitem.setOid(madeOrders.getOid());
            orderitem.setBid(cart.getBid());
            orderitem.setCount(cart.getCount());
            //?????????????????????
            Float subtotal = Float.valueOf(Math.round(bookService.getPriceById(cart.getBid()) * cart.getCount() * 100)) / 100;
            orderitem.setSubtotal(subtotal);
            orderitemList.add(orderitem);
        }
        //??????????????????
        boolean flag = orderitemService.saveBatch(orderitemList);
        HashMap hm = new HashMap();
        hm.put("oid",madeOrders.getOid());
        Object data = hm;
        //???????????????????????????
        ordersService.setOrdersTotal(madeOrders, orderitemList);
        //?????????????????????????????????
        cartService.removeCarts(cartList);

        return new R(flag, data);
    }



}
