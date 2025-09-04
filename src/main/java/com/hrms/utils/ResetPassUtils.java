package com.hrms.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
public class ResetPassUtils {
    private static final int CODE_LENGTH = 6;
    private static final long EXPIRE_TIME = 10; // 验证码有效期10分钟
    private static final String REDIS_PREFIX = "reset_password_code:";

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private EmailSenderUtils emailSenderUtils;

    /**
     * 生成验证码并发送到邮箱
     * @param email 接收验证码的邮箱
     * @return 发送成功返回 true，其他情况返回 false
     */
    public String getAuthCode(String email) {
        // 检查Redis中是否已经有此邮箱对应的修改密码页url后缀
        String redisKey = REDIS_PREFIX + email;
        String existingCode = redisTemplate.opsForValue().get(redisKey);

        // 用于存储上次发送修改密码页url后缀的时间戳
        String timestampKey = redisKey + ":timestamp";
        String lastSentTimeStr = redisTemplate.opsForValue().get(timestampKey);

        //获取当前的系统时间
        long currentTime = System.currentTimeMillis();

        String message = "请您跳转至以下页面继续完成操作：http://com.easychat/ResetPasswordPage/";
        //如果从redis中得到了上一次的内容
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
                return String.valueOf(timeInSecond);
            }
            // 同时存储当前时间戳，用于记录发送时间
            redisTemplate.opsForValue().set(timestampKey, String.valueOf(currentTime));
            // 如果时间间隔已满1分钟，发送Redis中已有的验证码
            message += existingCode;
            emailSenderUtils.sendSimpleMessage(email, message, "正在修改 EasyChat 密码");
            return existingCode;
        }
        // 如果没有，生成新的验证码
        String newCode = generateCode();
        // 存入Redis，设置过期时间为10分钟
        redisTemplate.opsForValue().set(redisKey, newCode, EXPIRE_TIME, TimeUnit.MINUTES);
        // 同时存储当前时间戳，用于记录发送时间
        redisTemplate.opsForValue().set(timestampKey, String.valueOf(currentTime), EXPIRE_TIME, TimeUnit.MINUTES);
        // 发送验证码到邮箱
        message += newCode;
        emailSenderUtils.sendSimpleMessage(email, message, "正在修改 EasyChat 密码");
        return newCode;
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
            return "签名已过期，请重新获取本页";
        }

        //2.验证码错误
        if (code.equals(storedCode)) {
            // 验证成功后从Redis中删除验证码
            redisTemplate.delete(redisKey);
            // 删除获取修改密码页的时间戳
            redisTemplate.delete(redisKey + ":timestamp");
            return null;
        }

        return "非法的访问请求";
    }

    /**
     * 生成6位数字验证码
     * @return 6位数字验证码
     */
    private String generateCode() {
        String uuid1 = UUID.randomUUID().toString().replace("-", "");
        String uuid2 = UUID.randomUUID().toString().replace("-", "");
        return uuid1 + uuid2; // 生成128位随机字符串
    }
}
