package com.impoara.bookstore.controller;

import com.impoara.bookstore.controller.utils.R;
import com.impoara.bookstore.domain.User;
import com.impoara.bookstore.service.impl.UserServiceImpl;
import com.impoara.bookstore.utils.ValidateImageCodeUtils;
import com.impoara.bookstore.utils.email.entity.SimpleEmailEntity;
import com.impoara.bookstore.utils.email.send.EmailSend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @Autowired
    private UserServiceImpl userService;
    //邮件发送功能
    @Autowired
    private EmailSend emailSend;

    //公共的验证码保存地方,改用发有重大隐患,但我不太会保存token导致session不同,无法获取到里面的值
    private String isCode = null;

    //登录功能
    @PostMapping("/login")
    public R Login(@RequestBody User user){
        R r = new R();
        User login = userService.Login(user);
        HashMap hm = new HashMap();
        //用户信息
        hm.put("userInfo",login);
        //flag
        Boolean flag = false;
        //判断账号是否激活
        if(userService.isActice(user)){
            //处理账号密码
            if(login != null){
                flag = true;
            }else {
                r.setMsg("账号或密码错误!");
            }
        }else {
            r.setMsg("您的账号尚未激活!");
        }
        hm.put("flag",flag);
        Object data = hm;
        r.setFlag(true);
        r.setData(data);
        //登陆成功后...
        return r;
    }

    //注册功能
    @PostMapping("/register")
    public R Register(@RequestBody User user, HttpServletRequest request){
        Object data = null;
        HashMap hm = new HashMap();
        //验证码错误
        if (!user.getCode().equals(isCode)) {
            hm.put("status",-1);
            data = hm;
            return new R(true,data,"验证码错误!");
        }
        //该用户名已存在则创建失败,不存在则创建成功
        if (!userService.Register(user)){
            //status = -1 失败, 0 成功
            hm.put("status",-1);
            data = hm;
            return new R(true,data,"该用户已存在!");
        }else {
            hm.put("status",0);
            data = hm;
            //发送邮件给用户
            sendEmailToCheck(user);
            return new R(true,data,"注册成功!");
        }

    }

    //在注册方法中向用户发送验证邮件
    public void sendEmailToCheck(User user){
        String subject = "请激活您的帐户 ------ 来自 天天图书馆";
        String content = "<h2>"+user.getUsername()+",请激活您的帐户</h2><a href='http://localhost/user/active?username="+user.getUsername()+"&code="+user.getCode()+"'>点击链接激活账户</a>" +
                "<hr/><span style='color: red;'>若非本人操作,请不要点击该链接!</span>" +
                "<p><img style='height: 200px;' src='cid:img01''></p>";
        String[] tos = {user.getEmail()};
        SimpleEmailEntity email = new SimpleEmailEntity(subject, content, tos);
        File file = new File("src/main/resources/static/hutao.png");
        emailSend.sendMail_HTML_PIC(email, file);
    }

    //激活账户功能
    @GetMapping("/active")
    public String activateAccount(User user){
        //激活账户(返回信息)
        return userService.ActiveAccount(user);
    }

    //发送验证码功能
    @GetMapping("/code")
    public void VerifyCode(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("image/jpeg");
        // 不缓存此内容
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);

        try {
            HttpSession session = request.getSession();
            //获取验证码
            String code = ValidateImageCodeUtils.getSecurityCode();
            //获取图片
            BufferedImage image = ValidateImageCodeUtils.createImage(code);
            session.removeAttribute("verifyCode");
            session.setAttribute("verifyCode",code);
            isCode = "";
            isCode = code;
            //设置session不会过期
            session.setMaxInactiveInterval(-1);
            ImageIO.write(image,"JPEG",response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
