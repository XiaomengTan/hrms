package com.hrms.index.service.impl;

import com.hrms.annotation.Log;
import com.hrms.domain.user.dto.LoginFormDTO;
import com.hrms.domain.user.dto.RegisterFormDTO;
import com.hrms.index.service.IndexService;
import com.hrms.utils.AuthCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@Service
public class IndexServiceImpl  implements IndexService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AppSettingsMapper appSettingsMapper;

    @Autowired
    private AuthCodeUtils authCodeUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;
//    private static final String LOGIN_ATTEMPTS_PREFIX = "loginAttempts:";
//    private static final String LOGIN_COOLDOWN_PREFIX = "loginCooldown:";
//
//    @Autowired
//    private RedisTemplate<String, Object> redisTemplate;
    @Log
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void register(RegisterFormDTO registerFormDTO) {
        //数据提取
        String email = registerFormDTO.getEmail();
        String password = registerFormDTO.getPassword();
        String confirmPass = registerFormDTO.getConfirmPass();
        String authCode = registerFormDTO.getAuthCode();
        //1.数据的业务逻辑规范检查

        //1.1检查用户是否同意用户协议
        if(!registerFormDTO.isAgreeState()){
            throw new RuntimeException("请先同意用户使用协议");
        }
        //1.2检查注册的邮箱是否存在于数据库表中
        User byEmailUser = userMapper.selectByEmail(email);
        //如果该邮箱已经注册过账户了
        if(byEmailUser != null){
            throw new RuntimeException("邮箱已注册");
        }
        //1.3检查确认密码与原密码是否一致
        if (!password.equals(confirmPass)) {
            throw new RuntimeException("密码前后不一致");
        }
        //1.4检查验证码是否正确填入且未过期
        String testAuthCode = authCodeUtils.validateCode(email, authCode);
        if (testAuthCode != null) {
            throw new RuntimeException(testAuthCode);
        }

        //2.注册

        //2.1数据处理
        //密码加密处理，使用BCrypt加密算法
        String encodePassword = passwordEncoder.encode(password);
        //创建一个用户对象和配置对象
        User user = new User();
        //补充user中的信息
        user.setEmail(email);
        user.setPassword(encodePassword);
        //截取邮箱前六个字符作为默认用户名的后缀，昵称与之相同
        user.setName("EC_USER_" + email.substring(0, 6));//EC_USER_130635
        user.setNickname(user.getName());
        user.setCreateTime(LocalDateTime.now());

        //2.2创建配置对象
        AppSettings appSettings = new AppSettings();
        //在用户配置表中添加配置
        appSettingsMapper.insert(appSettings);
        user.setSettingsId(appSettings.getId());

        //3.添加
        userMapper.insert(user);
    }

    @Log
    @Override
    public User login(LoginFormDTO loginFormDTO) {
        String email = loginFormDTO.getEmail();
        String password = loginFormDTO.getPassword();

//        // 检查用户是否在冷却时间内
//        String cooldownKey = LOGIN_COOLDOWN_PREFIX + email;
//        Long cooldownTime = (Long) redisTemplate.opsForValue().get(cooldownKey);
//        long currentTime = System.currentTimeMillis();
//        if (cooldownTime != null && cooldownTime > currentTime) {
//            throw new RuntimeException("请稍后再试，登录暂时被锁定。");
//        }

        //2. 获取用户
        User user = userMapper.selectByEmail(email);
        if (user == null) {
            return null;
        }

        //3. 校验密码是否正确
        if (!passwordEncoder.matches(password, user.getPassword())) {
            // 处理登录失败逻辑
//            handleLoginFailure(email);
            throw new RuntimeException("密码错误，请重新输入");
        }

        // 登录成功，重置失败计数
//        resetLoginAttempts(email);
        return user;
    }

    @Log
    @Override
    public void sendAuthCode(String email) {
        String sendState = authCodeUtils.generateAndSendCode(email);
        if (sendState != null) {
            log.error("发送状态：发送失败");
            log.error("发送状态：" + sendState);
            throw new RuntimeException(sendState);
        }
    }

//    /**
//     * 处理登录失败逻辑
//     */
//    private void handleLoginFailure(String email) {
//        String attemptsKey = LOGIN_ATTEMPTS_PREFIX + email;
//        String cooldownKey = LOGIN_COOLDOWN_PREFIX + email;
//
//        // 获取当前失败次数
//        Integer attempts = (Integer) redisTemplate.opsForValue().get(attemptsKey);
//        if (attempts == null) {
//            attempts = 0;
//        }
//        attempts++;
//        // 计算冷却时间
//        long cooldownDuration = 0;
//        if (attempts >= 4) {
//            switch (attempts) {
//                case 4:
//                    cooldownDuration = 1 * 60 * 1000L; // 1分钟
//                    break;
//                case 5:
//                    cooldownDuration = 5 * 60 * 1000L; // 5分钟
//                    break;
//                case 6:
//                    cooldownDuration = 10 * 60 * 1000L; // 10分钟
//                    break;
//                default:
//                    cooldownDuration = 30 * 60 * 1000L; // 30分钟
//                    break;
//            }
//        }
//
//        // 设置冷却时间
//        redisTemplate.opsForValue().set(cooldownKey, System.currentTimeMillis() + cooldownDuration, cooldownDuration, TimeUnit.MILLISECONDS);
//
//        // 更新失败次数，并设置TTL
//        redisTemplate.opsForValue().set(attemptsKey, attempts, 30, TimeUnit.MINUTES); // 错误次数的TTL可以根据需要设置
//    }
//
//    /**
//     * 登录成功时，重置登录失败次数
//     */
//    private void resetLoginAttempts(String email) {
//        String attemptsKey = LOGIN_ATTEMPTS_PREFIX + email;
//        String cooldownKey = LOGIN_COOLDOWN_PREFIX + email;
//
//        redisTemplate.delete(attemptsKey);
//        redisTemplate.delete(cooldownKey);
//    }
}
