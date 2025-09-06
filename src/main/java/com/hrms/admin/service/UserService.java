package com.hrms.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hrms.domain.admin.dto.UserDTO;
import com.hrms.domain.common.dto.PageDataDTO;
import com.hrms.domain.common.entity.User;
import com.hrms.domain.common.pojo.PageBean;

public interface UserService extends IService<User> {
    /**
     * 添加用户
     * @param userDTO
     */
    void addUser(UserDTO userDTO);

    /**
     * 获取用户列表
     * @param pageDataDTO
     * @return
     */
    PageBean getUserList(PageDataDTO pageDataDTO);
}
