package com.impoara.bookstore.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orders {
    /**
     * 订单表
     */
    //订单主键
    @TableId
    private Integer oid;
    //订单生成时间
    private String ordertime;
    //订单合计
    private Float total;
    //订单状态：未付款、已付款但未发货、已发货但未确认收货、收货已结束:1为成功,0为失败

    private Integer state;
    //订单的主人Id
    private Integer uid;
    //订单的收货地址
    private String address;
    //收件人电话
    private String phone;

    @TableField(exist = false)
    private String username;
}
