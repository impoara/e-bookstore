package com.impoara.bookstore.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.impoara.bookstore.domain.Book;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BookDao extends BaseMapper<Book> {
}
