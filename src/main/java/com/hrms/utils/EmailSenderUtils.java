package com.hrms.utils;

import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamSource;
import org.springframework.core.io.UrlResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.List;

@Slf4j
@Component
public class EmailSenderUtils {
    @Value("${spring.mail.username}")
    private String fromEmail;

    @Autowired
    private JavaMailSender mailSender;

    public void sendSimpleMessage(String email, String content, String subject) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(fromEmail);
            message.setTo(email);
            message.setSubject(subject);
            message.setText(content);
            mailSender.send(message);
        } catch (Exception e) {
            log.error(e.getMessage());
            log.error("邮箱错误");
            throw new RuntimeException("邮箱错误");
        }
    }

    public void sendComplexMessage(String email, String content, String subject, String cover, List<String> attachments) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            helper.setFrom(fromEmail);
            helper.setTo(email);
            helper.setSubject(subject);

            // 构建带图片的邮件内容
            StringBuilder emailContent = new StringBuilder();
            if (cover != null) {
                URL coverUrl = new URL(cover);
                InputStreamSource coverImage = new UrlResource(coverUrl);
                helper.addInline("coverImage", coverImage, "image/jpeg");
                emailContent.append("<img src='cid:coverImage'><br><br>");
            }
            emailContent.append(content);

            // 设置邮件内容为HTML格式
            helper.setText(emailContent.toString(), true);

            // 添加附件
            if (!attachments.isEmpty()) {
                for (String attachmentUrl : attachments) {
                    URL url = new URL(attachmentUrl);
                    InputStreamSource attachment = new UrlResource(url);
                    String filename = attachmentUrl.substring(attachmentUrl.lastIndexOf('/') + 1);
                    helper.addAttachment(filename, attachment);
                }
            }

            // 发送邮件
            mailSender.send(mimeMessage);
        } catch (Exception e) {
            log.error(e.getMessage());
            log.error("邮箱发送失败");
            throw new RuntimeException("邮箱发送失败", e);
        }
    }

    public void testEmail(String email){
        //1.1检查是否符合要求
        if(!(email.matches("^.{1,64}$")
                && email.matches("^[a-zA-Z0-9_-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"))){
            throw new RuntimeException("不符合正则表达式 " +
                    "\"" +
                    email +
                    "\""+
                    ".");
        }
    }
}
