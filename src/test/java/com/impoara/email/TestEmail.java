package com.impoara.email;

import com.impoara.bookstore.utils.email.entity.SimpleEmailEntity;
import com.impoara.bookstore.utils.email.send.EmailSend;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

@SpringBootTest
public class TestEmail {
    @Autowired
    private EmailSend emailSend;

    @Test
    void SendEmailTest1(){
        String subject = "请激活您的帐户 ------ 天天图书馆";
        String content = "可惜这个没有链接";
        String[] tos = {"impoara@qq.com"};
        SimpleEmailEntity email = new SimpleEmailEntity(subject,content,tos);
        emailSend.sendSimpleMail(email);
    }

    @Test
    void SendEmailTest2(){
        String subject = "请激活您的帐户 ------ 天天图书馆";
        String content = "<h2>请激活您的帐户</h2><a href='https://www.51kim.com/'>点击链接激活账户</a><hr/><span style='color: red;'>若非本人操作,请不要点击该链接!</span>";
        String[] tos = {"impoara@qq.com"};
        SimpleEmailEntity email = new SimpleEmailEntity(subject,content,tos);
        emailSend.sendMail_HTML(email);
    }

    @Test
    void SendEmailTest3() {
        String subject = "请激活您的帐户 ------ 天天图书馆";
        String content = "<h2>请激活您的帐户</h2><a href='https://www.51kim.com/'>点击链接激活账户</a><hr/><span style='color: red;'>若非本人操作,请不要点击该链接!</span>";
        String[] tos = {"impoara@qq.com"};
        SimpleEmailEntity email = new SimpleEmailEntity(subject, content, tos);
        File file = new File("src/main/resources/static/riven.png");
        emailSend.sendMail_HTML_FJ(email, file);
    }

    @Test
    void SendEmailTest4() {
        String subject = "请激活您的帐户 ------ 天天图书馆";
        String content = "<h2>请激活您的帐户</h2><a href='https://www.51kim.com/'>点击链接激活账户</a>" +
                "<hr/><span style='color: red;'>若非本人操作,请不要点击该链接!</span>" +
                "<p><img style='height: 200px;' src='cid:img01''></p>";
        String[] tos = {"impoara@qq.com"};
        SimpleEmailEntity email = new SimpleEmailEntity(subject, content, tos);
        File file = new File("src/main/resources/static/riven.png");
        emailSend.sendMail_HTML_PIC_FJ(email, file);
    }

    @Test
    void SendEmailTest5() {
        String subject = "请激活您的帐户 ------ 天天图书馆";
        String content = "<h2>请激活您的帐户</h2><a href='https://www.51kim.com/'>点击链接激活账户</a>" +
                "<hr/><span style='color: red;'>若非本人操作,请不要点击该链接!</span>" +
                "<p><img style='height: 200px;' src='cid:img01''></p>";
        String[] tos = {"impoara@qq.com"};
        SimpleEmailEntity email = new SimpleEmailEntity(subject, content, tos);
        File file = new File("src/main/resources/static/hutao.png");
        emailSend.sendMail_HTML_PIC(email, file);
    }
}
