package com.impoara.bookstore.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.impoara.bookstore.domain.Cart;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CartDao extends BaseMapper<Cart> {
}
