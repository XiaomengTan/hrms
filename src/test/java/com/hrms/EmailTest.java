package com.hrms;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootTest
public class EmailTest {
    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail; // 获取配置文件中的发件人邮箱地址

//    @Test
//    public void test() {
//        ValueOperations valueOperations = redisTemplate.opsForValue();
//        valueOperations.set("username", "zhangsan");
//    }

    @Test
    public void testEmail() {
        String email = "1306357935@qq.com";
        String code = "123456";
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(fromEmail);
            message.setTo(email);
            message.setSubject("验证码");
            message.setText("您的验证码是：" + code + "，有效期为10分钟。");
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
