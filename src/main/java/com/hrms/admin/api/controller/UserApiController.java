package com.hrms.admin.api.controller;

import com.hrms.admin.api.UserApi;
import com.hrms.admin.service.UserService;
import com.hrms.domain.admin.dto.UserDTO;
import com.hrms.domain.common.dto.PageDataDTO;
import com.hrms.domain.common.pojo.PageBean;
import com.hrms.domain.common.pojo.Result;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class UserApiController implements UserApi {

    @Autowired
    private UserService userService;

    @Override
    public Result addUser(UserDTO userDTO) {
        log.info("添加用户：{}", userDTO.toString());
        userService.addUser(userDTO);
        log.info("添加用户成功");
        return Result.success("添加用户成功");
    }

    @Override
    public Result deleteUser(Integer userId) {
        log.info("即将删除用户：{}", userId);
        userService.removeById(userId);
        log.info("用户删除成功");
        return Result.success("用户删除成功");
    }

    @Override
    public Result<PageBean> getUserList(PageDataDTO pageDataDTO) {
        log.info("分页获取用户列表：{}", pageDataDTO);
        PageBean pageBean = userService.getUserList(pageDataDTO);
        log.info("获取成功");
        return Result.success(pageBean);
    }
}
