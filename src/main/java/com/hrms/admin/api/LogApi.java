package com.hrms.admin.api;

import com.hrms.domain.common.dto.PageFullDataDTO;
import com.hrms.domain.common.pojo.PageBean;
import com.hrms.domain.common.pojo.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "系统日志管理", description = "系统日志管理")
@RequestMapping("/admin/log")
@Validated
public interface LogApi {

    @Operation(summary = "分页查询日志信息")
    @GetMapping("/getOptionalByPage")
    Result<PageBean> getOptionalByPage(@ModelAttribute @Validated PageFullDataDTO pageFullDataDTO);

    @Operation(summary = "按日期分页查询日志信息")
    @GetMapping("/getOptionalByDate")
    Result<PageBean> getOperationByDate(@ModelAttribute @Validated PageFullDataDTO pageFullDataDTO);
}
