package com.impoara.bookstore.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.impoara.bookstore.domain.Orderitem;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderItemDao extends BaseMapper<Orderitem> {
}
