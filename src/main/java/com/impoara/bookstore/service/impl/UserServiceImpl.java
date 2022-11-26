package com.impoara.bookstore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.impoara.bookstore.controller.utils.ProjectExceptionAdvice;
import com.impoara.bookstore.dao.UserDao;
import com.impoara.bookstore.domain.User;
import com.impoara.bookstore.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements IUserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User getById(Integer id) {
        QueryWrapper qw = new QueryWrapper();
        qw.eq("uid",id);
        return userDao.selectOne(qw);
    }

    @Override
    public User Login(User user) {
        QueryWrapper qw = new QueryWrapper();
        qw.eq("username", user.getUsername());
        User login = userDao.selectOne(qw);
        if (login == null) return null;
        if (user.getPassword().equals(login.getPassword())){
            return login;
        }else {
            return null;
        }
    }



    @Override
    public boolean Register(User user) {
        QueryWrapper qw = new QueryWrapper();
        qw.eq("username", user.getUsername());
        User register = userDao.selectOne(qw);
        if (register != null){
            return false;
        }else {
            user.setState(0);
            return userDao.insert(user) > 0;
        }
    }

    @Override
    public Boolean isActice(User user) {
        QueryWrapper qw = new QueryWrapper();
        qw.eq("username", user.getUsername());
        User account = userDao.selectOne(qw);
        if (account == null) return false;
        if (account.getState() == 1){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public String getNameById(Integer uid) {
        return userDao.selectById(uid).getUsername();
    }

    @Override
    public String ActiveAccount(User user) {
        QueryWrapper qw = new QueryWrapper();
        qw.eq("username", user.getUsername());
        User active = userDao.selectOne(qw);
        if (active == null){
            return "该用户不存在,请进行注册!";
        } else if (!active.getCode().equals(user.getCode())){
            return "您的激活方式不合法,请点击邮件链接激活,或请联系管理员激活。";
        } else if (active.getState() == 1){
            return "您的账户已经激活过了,无需再次激活。";
        } else if (active.getState() == 0){
            active.setState(1);
            userDao.updateById(active);
            return "恭喜,您的账户已经激活成功!";
        } else{
            return "您的账户出现异常,现已无法激活,详情请联系管理员。";
        }
    }
}
