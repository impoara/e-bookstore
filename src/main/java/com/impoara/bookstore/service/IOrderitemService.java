package com.impoara.bookstore.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.impoara.bookstore.domain.Orderitem;

import java.util.List;

public interface IOrderitemService extends IService<Orderitem> {
    Orderitem getById(Integer id);

    List<Orderitem> getListByOid(Integer oid);
}
