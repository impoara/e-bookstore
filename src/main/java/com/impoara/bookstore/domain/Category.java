package com.impoara.bookstore.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    /**
     * 分类
     */
    //分类主键
    @TableId
    private Integer cid;
    //分类名称
    private String cname;
}
