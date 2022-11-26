package com.impoara.bookstore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.impoara.bookstore.dao.CategoryDao;
import com.impoara.bookstore.domain.Category;
import com.impoara.bookstore.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, Category> implements ICategoryService {
    @Autowired
    private CategoryDao categoryDao;

    @Override
    public Category getById(Integer id) {
        QueryWrapper qw = new QueryWrapper();
        qw.eq("cid",id);
        return categoryDao.selectOne(qw);
    }
}
