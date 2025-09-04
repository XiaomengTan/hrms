package com.hrms.index.api;

import com.hrms.domain.common.pojo.Result;
import com.hrms.domain.common.dto.LoginFormDTO;

import io.swagger.v3.oas.annotations.Operation;

import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "公共页面服务", description = "提供登录和注册功能")
@RequestMapping("/index")
@Validated
public interface IndexApi {

    @Operation(summary = "用户登录接口")
    @PostMapping("/login")
    Result<String> login(@RequestBody @Validated @NotNull LoginFormDTO loginFormDTO);

    @Operation(summary = "测试用-添加用户接口")
    @PostMapping("/addUser")
    Result<String> addUser(@RequestBody @Validated @NotNull LoginFormDTO loginFormDTO);

}
