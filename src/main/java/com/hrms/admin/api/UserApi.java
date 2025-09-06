package com.hrms.admin.api;

import com.hrms.domain.admin.dto.UserDTO;
import com.hrms.domain.common.dto.PageDataDTO;
import com.hrms.domain.common.pojo.PageBean;
import com.hrms.domain.common.pojo.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "系统用户管理", description = "系统用户管理")
@RequestMapping("/admin/user")
@Validated
public interface UserApi {

    @Operation(summary = "添加用户")
    @PostMapping("/addUser")
    Result addUser(@RequestBody @Validated UserDTO userDTO);

    @Operation(summary = "删除用户")
    @DeleteMapping("/deleteUser/{userId}")
    Result deleteUser(@PathVariable @NotNull Integer userId);

    @Operation(summary = "分页获取用户列表")
    @GetMapping("/getUserList")
    Result<PageBean> getUserList(@ModelAttribute @NotNull PageDataDTO pageDataDTO);

}
