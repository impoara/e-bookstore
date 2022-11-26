package com.impoara.bookstore.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    /**
     * 用户表
     */
    //用户主键
    @TableId
    private Integer uid;
    //用户名
    private String username;
    //密码
    private String password;
    //邮箱
    private String email;
    //验证码
    private String code;
    //用户状态(是否激活):1为激活,0为未激活
    private Integer state;
}
