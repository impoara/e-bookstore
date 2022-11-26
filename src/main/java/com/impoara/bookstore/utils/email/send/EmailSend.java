package com.impoara.bookstore.utils.email.send;

import com.impoara.bookstore.utils.email.entity.SimpleEmailEntity;

import java.io.File;

public interface EmailSend {
    void sendSimpleMail(SimpleEmailEntity simpleEmailEntity);

    void sendMail_HTML(SimpleEmailEntity simpleEmailEntity);

    void sendMail_HTML_FJ(SimpleEmailEntity simpleEmailEntity, File file);

    void sendMail_HTML_PIC(SimpleEmailEntity simpleEmailEntity, File file);

    void sendMail_HTML_PIC_FJ(SimpleEmailEntity simpleEmailEntity, File file);
}
