package com.hrms.admin.api;

import com.hrms.domain.admin.dto.PaymentDTO;
import com.hrms.domain.common.dto.PageFullDataDTO;
import com.hrms.domain.common.pojo.PageBean;
import com.hrms.domain.common.pojo.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "系统日志管理", description = "系统日志管理")
@RequestMapping("/admin/payment")
@Validated
public interface PaymentApi {

    @Operation(summary = "定时添加收支账单")
    void addPaymentForm();

    @PostMapping("/add")//仅用于测试
    void addPaymentFormForTest();

    @Operation(summary = "有关键词时模糊匹配全列，没有关键词时直接分页搜索全部")
    @GetMapping("/getByPage")
    Result<PageBean> getPaymentByPage(@ModelAttribute @Validated PageFullDataDTO pageFullDataDTO);

    @Operation(summary = "修改某日的收支信息")
    @PutMapping("/revise")
    Result revisePayment(@RequestBody @Validated PaymentDTO paymentDTO);

}
