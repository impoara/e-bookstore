package com.impoara.bookstore.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.impoara.bookstore.domain.User;

public interface IUserService extends IService<User> {

    User getById(Integer id);

    User Login(User user);

    boolean Register(User user);

    String ActiveAccount(User user);

    Boolean isActice(User user);

    String getNameById(Integer uid);
}
