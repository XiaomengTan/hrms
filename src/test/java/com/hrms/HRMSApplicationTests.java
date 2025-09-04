package com.hrms;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HRMSApplicationTests {

    @Test
    void regex() {
        String s = "afgqfge/$file_type/$";
        System.out.println(s.matches("/\\$file_type/\\$"));
        System.out.println(s.contains("/$file_type/$"));
    }

    @Test
    void LongValueTest() {
        long channelId = 123456789012L;
        long lastSixDigits = channelId % 1000000;

        String result = "EC_CHANNEL" + lastSixDigits;

        System.out.println(result);
    }

}
