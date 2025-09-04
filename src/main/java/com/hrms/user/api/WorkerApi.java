package com.hrms.user.api;

import com.hrms.domain.common.pojo.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@Tag(name = "动态管理", description = "动态相关操作接口")
@RequestMapping("/user/activity")
public interface WorkerApi {

}

