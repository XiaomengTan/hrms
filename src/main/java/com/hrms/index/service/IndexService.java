package com.hrms.index.service;

import com.hrms.domain.user.dto.LoginFormDTO;
import com.hrms.domain.user.dto.RegisterFormDTO;
import com.hrms.domain.common.entity.User;

public interface IndexService {
    /**
     * 注册
     * @param registerFormDTO
     */
    void register(RegisterFormDTO registerFormDTO);

    /**
     * 登录操作
     * teleport可以加入一个限制频繁登录的检查
     * teleport最好是在LoginCheckInterceptor拦截器里面加入
     * teleport另外还需要再添加一个拦截器来阻止同一jwt的高频访问
     * @param loginFormDTO
     * @return
     */
    User login(LoginFormDTO loginFormDTO);

    /**
     * 发送验证码
     * @param email
     */
    void sendAuthCode(String email);
}
