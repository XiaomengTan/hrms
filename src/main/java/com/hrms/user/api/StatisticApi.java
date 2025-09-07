package com.hrms.user.api;


import com.hrms.domain.common.dto.PageDataDTO;
import com.hrms.domain.common.entity.Department;
import com.hrms.domain.common.pojo.PageBean;
import com.hrms.domain.common.pojo.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Tag(name = "数据统计", description = "数据统计相关接口")
@Validated
@RequestMapping("/user/statistic")
public interface StatisticApi {


    @Operation(summary = "分页获取部门")
    @GetMapping("/getAllDept")
    Result<List<Department>> getAllDept();
}
