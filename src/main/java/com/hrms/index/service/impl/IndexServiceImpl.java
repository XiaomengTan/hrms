package com.hrms.index.service.impl;

import com.hrms.annotation.Log;
import com.hrms.domain.common.dto.LoginFormDTO;
import com.hrms.domain.common.entity.User;
import com.hrms.index.service.IndexService;
import com.hrms.user.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class IndexServiceImpl  implements IndexService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Log
    @Override
    public User login(LoginFormDTO loginFormDTO) {
        String username = loginFormDTO.getUsername();
        String password = loginFormDTO.getPassword();

        //1. 获取用户
        User user = userMapper.getByUserName(username);
        if (user == null) {
            throw new RuntimeException("不存在该用户");
        }

        //3. 校验密码是否正确
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("密码错误，请重新输入");
        }
        return user;
    }

    /**
     * 测试时添加用户
     *
     * @param loginFormDTO
     */
    @Override
    public void addUser(LoginFormDTO loginFormDTO) {
        log.info(loginFormDTO.toString());
        User user = new User();
        user.setName(loginFormDTO.getUsername());
        user.setPassword(passwordEncoder.encode(loginFormDTO.getPassword()));
        user.setWorker(null);
        userMapper.insert(user);
    }

    /*
    使用spring security做密码加密
    0.声明bean
        首先在使用加密操作的类中声明bean
        @Autowired
        private PasswordEncoder passwordEncoder;
    1.注册：
        将传入的密码password使用passwordEncoder.encode(password);进行加密，加密后存储
    2.登录：
        将用户输入的密码enPassword使用passwordEncoder.matches(enPassword, user.getPassword())校验，校验通过将返回true
     */
}
