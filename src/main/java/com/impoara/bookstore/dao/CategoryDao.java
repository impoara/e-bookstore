package com.impoara.bookstore.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.impoara.bookstore.domain.Category;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryDao extends BaseMapper<Category> {
}
