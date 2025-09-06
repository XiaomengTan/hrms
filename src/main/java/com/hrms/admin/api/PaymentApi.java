package com.hrms.admin.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "系统日志管理", description = "系统日志管理")
@RequestMapping("/admin/payment")
@Validated
public interface PaymentApi {

    @Operation(summary = "定时任务测试")
//    @PostMapping("/add")仅用于测试
    void addPaymentForm();
}
