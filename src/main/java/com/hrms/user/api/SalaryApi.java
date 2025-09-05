package com.hrms.user.api;

import com.hrms.domain.common.entity.Salary;
import com.hrms.domain.common.pojo.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "薪资管理", description = "薪资相关接口")
@Validated
@RequestMapping("/user/salary")
public interface SalaryApi {

    @Operation(summary = "添加薪资条")
    @PostMapping("/add")
    Result addSalary(@RequestBody @Validated Salary salary);

}
