package com.hrms.index.api;

import com.hrms.domain.common.pojo.Result;
import com.hrms.domain.user.dto.LoginFormDTO;
import com.hrms.domain.user.dto.RegisterFormDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "公共页面服务", description = "提供登录和注册功能")
@RequestMapping("/index")
@Validated
public interface IndexApi {

    @Operation(summary = "用户注册接口")
    @PostMapping("/register")
    Result register(@RequestBody @Validated @NotNull RegisterFormDTO registerFormDTO);

    @Operation(summary = "用户登录接口")
    @PostMapping("/login")
    Result<String> login(@RequestBody @Validated @NotNull LoginFormDTO loginFormDTO);

    @Operation(summary = "注册时的验证码校验")
    @PostMapping("/code")
    Result sendAuthCode(@RequestParam @NotEmpty String email);//optimize要封装到dto类中，下面那个也是 finish
    // finish 没有封装到dto中，而是直接使用@RequestParam接收参数
}
