package com.hrms.admin.api.controller;

import com.hrms.admin.api.UserApi;
import com.hrms.admin.service.UserService;
import com.hrms.domain.admin.dto.UserDTO;
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
}
