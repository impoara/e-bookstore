package com.impoara.bookstore.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    /**
     * 图书表
     */
    //图书主键
    @TableId
    private Integer bid;
    //图书名
    private String bname;
    //单价
    private Float price;
    //作者
    private String author;
    //图片
    private String image;
    //所属分类
    private Integer cid;

    //分类(数据库未定义)
    @TableField(exist = false)
    private String btype;

}
