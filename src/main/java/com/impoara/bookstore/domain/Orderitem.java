package com.impoara.bookstore.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orderitem {
    /**
     * 订单详情
     */
    //订单详情主键
    @TableId
    private Integer orid;
    //商品数量
    private Integer count;
    //商品计价
    private Float subtotal;
    //订单id
    private Integer oid;
    //图书id
    private Integer bid;
    //图书
    @TableField(exist = false)
    private Book book;
}
