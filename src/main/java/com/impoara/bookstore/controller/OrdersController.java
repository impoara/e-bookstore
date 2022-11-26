package com.impoara.bookstore.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.impoara.bookstore.controller.utils.R;
import com.impoara.bookstore.domain.Book;
import com.impoara.bookstore.domain.Orderitem;
import com.impoara.bookstore.domain.Orders;
import com.impoara.bookstore.service.IBookService;
import com.impoara.bookstore.service.IOrderitemService;
import com.impoara.bookstore.service.IOrdersService;
import com.impoara.bookstore.service.IUserService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/orders")
public class OrdersController {

    final IOrdersService ordersService;

    final IOrderitemService orderitemService;

    final IBookService bookService;

    final IUserService userService;

    OrdersController(IOrdersService ordersService,IOrderitemService orderitemService,IBookService bookService,IUserService userService){
        this.ordersService = ordersService;
        this.orderitemService = orderitemService;
        this.bookService = bookService;
        this.userService = userService;
    }

    //获取指定的订单和其Item
    @GetMapping("{oid}")
    public R getOrdersAndItemsByU_Oid(@PathVariable Integer oid){
        //订单数据
        Orders orders = ordersService.getById(oid);
        orders.setUsername(userService.getNameById(orders.getUid()));
        //订单详情数据
        List<Orderitem> orderitemList = orderitemService.getListByOid(oid);
        //图书数据
        for (Orderitem orderitem : orderitemList) {
            Book book = bookService.getById(orderitem.getBid());
            orderitem.setBook(book);
        }
        HashMap hm = new HashMap();
        hm.put("orders",orders);
        hm.put("orderitemList",orderitemList);
        Object data = hm;
        return new R(true,data);
    }

    //订单支付
    @PostMapping("/pay")
    public R payTheOrder(@RequestBody Orders orders){
        //保存地址和手机号到订单中
        ordersService.setOrdersAddressAndPhone(orders);
        //订单支付状态确认
        Orders getOrders = ordersService.getById(orders.getOid());
        //支付逻辑在这里写----------

        //-----------------------
        //更改订单支付状态为已支付 1
        ordersService.payToSetOrdersStateTo1(getOrders.getOid(),1);
        return new R(true);
    }

    //检查订单支付状态
    @GetMapping("pay/{oid}")
    public R checkIsPay(@PathVariable Integer oid){
        Orders getOrders = ordersService.getById(oid);
        //返回订单信息
        HashMap hm = new HashMap();
        hm.put("oid",getOrders.getOid());
        hm.put("state",getOrders.getState());
        Object data = hm;
        return new R(true,data);
    }

    //获取所有订单
    @GetMapping("/list/{currentPage}/{pageSize}/{uid}")
    public R getOrdersByUid(@PathVariable int currentPage, @PathVariable int pageSize, @PathVariable Integer uid, Orders orders){
        String username = userService.getNameById(uid);
        System.out.println(orders);
        IPage<Orders> ordersPage = ordersService.getPageBy(currentPage, pageSize, uid, orders);
        for (Orders record : ordersPage.getRecords()) {
            record.setUsername(username);
        }
        HashMap<String,IPage<Orders>> hm = new HashMap<>();
        hm.put("pages",ordersPage);
        Object data = hm;
        return new R(true,data);
    }

}
