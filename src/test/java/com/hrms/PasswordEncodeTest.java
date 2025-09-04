package com.hrms;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
@SpringBootTest
public class PasswordEncodeTest {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void test() {

        // 密码
        String rawPassword = "123456789";

        // 加密后的密码
        String encodedPassword = "$2a$10$6AU6ZrLRWYQfH/wfsV390.39d.MJzUIrTcOtEPx5oAM0Gg9NPjQkG";

        // 验证密码
        boolean isMatch = passwordEncoder.matches(rawPassword, encodedPassword);

        // 输出结果
        System.out.println("Password matches: " + isMatch);
    }
}
