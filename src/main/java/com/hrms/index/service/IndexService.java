package com.hrms.index.service;


import com.hrms.domain.common.dto.LoginFormDTO;
import com.hrms.domain.common.entity.User;

public interface IndexService {


    /**
     * 登录
     * @param loginFormDTO
     * @return
     */
    User login(LoginFormDTO loginFormDTO);

    /**
     * 测试时添加用户
     * @param loginFormDTO
     */
    void addUser(LoginFormDTO loginFormDTO);
}
