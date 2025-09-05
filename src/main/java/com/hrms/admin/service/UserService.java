package com.hrms.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hrms.domain.admin.dto.UserDTO;
import com.hrms.domain.common.entity.User;

public interface UserService extends IService<User> {
    /**
     * 添加用户
     * @param userDTO
     */
    void addUser(UserDTO userDTO);
}
