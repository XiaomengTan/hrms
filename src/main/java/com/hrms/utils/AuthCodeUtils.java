package com.hrms.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.TimeUnit;
@Slf4j
@Component
public class AuthCodeUtils {

    private static final int CODE_LENGTH = 6;
    private static final long EXPIRE_TIME = 10; // 验证码有效期10分钟
    private static final String REDIS_PREFIX = "verification_code:";

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private EmailSenderUtils emailSenderUtils;

    /**
     * 生成验证码并发送到邮箱
     * @param email 接收验证码的邮箱
     * @return 发送成功返回 true，其他情况返回 false
     */
    public String generateAndSendCode(String email) {
        // 检查Redis中是否已经有此邮箱对应的验证码
        String redisKey = REDIS_PREFIX + email;
        String existingCode = redisTemplate.opsForValue().get(redisKey);

        // 用于存储上次发送验证码的时间戳
        String timestampKey = redisKey + ":timestamp";
        String lastSentTimeStr = redisTemplate.opsForValue().get(timestampKey);

        long currentTime = System.currentTimeMillis();

        String message;
        if (existingCode != null) {
            // 将字符串转换为Long类型
            assert lastSentTimeStr != null;
            long lastSentTime = Long.parseLong(lastSentTimeStr);

            // 计算距离上次发送验证码的时间间隔（以毫秒为单位）
            long timeElapsed = currentTime - lastSentTime;

            // 如果时间间隔小于1分钟（60000毫秒），则不发送验证码
            if (timeElapsed < 60000) {
                int timeInSecond = (int) (timeElapsed / 1000);
                timeInSecond = 60 - timeInSecond;
                return "发送太频繁，请在"+ timeInSecond +"(s)后重新发送";
            }
            // 同时存储当前时间戳，用于记录发送时间
            redisTemplate.opsForValue().set(timestampKey, String.valueOf(currentTime));
            // 如果时间间隔已满1分钟，发送Redis中已有的验证码
            message = "您正在注册 EasyChat 账户，验证码是：" + existingCode + "，有效期为10分钟。";
            emailSenderUtils.sendSimpleMessage(email, message, "正在注册 EasyChat 账户");
            return null;
        }

        // 如果没有，生成新的验证码
        String newCode = generateCode();
        // 存入Redis，设置过期时间为10分钟
        redisTemplate.opsForValue().set(redisKey, newCode, EXPIRE_TIME, TimeUnit.MINUTES);
        // 同时存储当前时间戳，用于记录发送时间
        redisTemplate.opsForValue().set(timestampKey, String.valueOf(currentTime), EXPIRE_TIME, TimeUnit.MINUTES);
        // 发送验证码到邮箱
        message = "您正在注册【EasyChat】账户，验证码是：" + newCode + "，有效期为10分钟。";
        emailSenderUtils.sendSimpleMessage(email, message, "正在注册 EasyChat 账户");
        return null;
    }

    /**
     * 校验验证码
     * @param email 邮箱
     * @param code 验证码
     * @return 验证成功返回 true，其他情况返回 false
     */
    public String validateCode(String email, String code) {
        String redisKey = REDIS_PREFIX + email;
        String storedCode = redisTemplate.opsForValue().get(redisKey);
        //1.验证码已过期
        if(storedCode == null){
            return "验证码已过期，请重新获取";
        }

        //2.验证码正确
        if (code.equals(storedCode)) {
            // 验证成功后从Redis中删除验证码
            redisTemplate.delete(redisKey);
            //同时删除时间戳
            redisTemplate.delete(redisKey + ":timestamp");
            return null;
        }
        //3.验证码错误
        return "验证码错误";
    }

    /**
     * 生成6位数字验证码
     * @return 6位数字验证码
     */
    private String generateCode() {
        Random random = new Random();
        StringBuilder code = new StringBuilder(CODE_LENGTH);

        for (int i = 0; i < CODE_LENGTH; i++) {
            code.append(random.nextInt(10)); // 生成0-9的随机数字
        }

        return code.toString();
    }
}

