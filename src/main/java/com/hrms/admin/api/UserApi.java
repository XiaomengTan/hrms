package com.hrms.admin.api;

import com.hrms.domain.admin.dto.UserDTO;
import com.hrms.domain.common.pojo.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "系统用户管理", description = "系统用户管理")
@RequestMapping("/admin/user")
@Validated
public interface UserApi {

    @Operation(summary = "添加用户")
    @PostMapping("/addUser")
    Result addUser(@RequestBody @Validated UserDTO userDTO);

}
