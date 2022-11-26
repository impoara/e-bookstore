package com.impoara.bookstore.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    @TableId
    private Integer caid;

    private Integer bid;

    private Integer count;

    private Integer uid;

    @TableField(exist = false)
    private Book book;
}
