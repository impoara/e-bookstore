package com.impoara.bookstore.utils.email.send.sendImpl;

import com.impoara.bookstore.utils.email.entity.SimpleEmailEntity;
import com.impoara.bookstore.utils.email.send.EmailSend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class EmailSendImpl implements EmailSend {
    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Override
    public void sendSimpleMail(SimpleEmailEntity simpleEmailEntity) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setSubject(simpleEmailEntity.getSubject());
        message.setText(simpleEmailEntity.getContent());
        message.setTo(simpleEmailEntity.getTos());
        mailSender.send(message);
    }

    @Override
    public void sendMail_HTML(SimpleEmailEntity simpleEmailEntity) {
        //创建一个MINE消息
        MimeMessage message = mailSender.createMimeMessage();
        //发送者
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(message,true);
            //发送者
            messageHelper.setFrom(from);
            //接收者
            messageHelper.setTo(simpleEmailEntity.getTos());
            //邮件主题
            messageHelper.setSubject(simpleEmailEntity.getSubject());
            //邮件内容   true 表示带有附件或html
            messageHelper.setText(simpleEmailEntity.getContent(),true);
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendMail_HTML_FJ(SimpleEmailEntity simpleEmailEntity,File file) {
        //创建一个MINE消息
        MimeMessage message = mailSender.createMimeMessage();
        //发送者
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(message,true);
            //发送者
            messageHelper.setFrom(from);
            //接收者
            messageHelper.setTo(simpleEmailEntity.getTos());
            //邮件主题
            messageHelper.setSubject(simpleEmailEntity.getSubject());
            //添加附件
            messageHelper.addAttachment(file.getName(),file);
            //邮件内容   true 表示带有附件或html
            messageHelper.setText(simpleEmailEntity.getContent(),true);
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendMail_HTML_PIC(SimpleEmailEntity simpleEmailEntity, File file) {
        //创建一个MINE消息
        MimeMessage message = mailSender.createMimeMessage();
        //发送者
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(message,true);
            //发送者
            messageHelper.setFrom(from);
            //接收者
            messageHelper.setTo(simpleEmailEntity.getTos());
            //邮件主题
            messageHelper.setSubject(simpleEmailEntity.getSubject());
            //邮件内容   true 表示带有附件或html
            messageHelper.setText(simpleEmailEntity.getContent(),true);
            //图片占位写法  如果图片链接写入模板 注释下面这一行
//            messageHelper.addInline("img01",new FileSystemResource(file.getPath()));
            messageHelper.addInline("img01", file);
            //发送邮件
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendMail_HTML_PIC_FJ(SimpleEmailEntity simpleEmailEntity, File file) {
        //创建一个MINE消息
        MimeMessage message = mailSender.createMimeMessage();
        //发送者
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(message,true);
            //发送者
            messageHelper.setFrom(from);
            //接收者
            messageHelper.setTo(simpleEmailEntity.getTos());
            //邮件主题
            messageHelper.setSubject(simpleEmailEntity.getSubject());
            //添加附件
            messageHelper.addAttachment(file.getName(),file);
            //邮件内容   true 表示带有附件或html
            messageHelper.setText(simpleEmailEntity.getContent(),true);
            //图片占位写法  如果图片链接写入模板 注释下面这一行
            messageHelper.addInline("img01",new FileSystemResource(file.getPath()));
            //发送邮件
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }


}
