package com.impoara.bookstore.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.impoara.bookstore.domain.Category;

public interface ICategoryService extends IService<Category> {

    Category getById(Integer id);
}
