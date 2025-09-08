package com.hrms.user.api;


import com.hrms.domain.common.entity.Department;
import com.hrms.domain.common.entity.Salary;
import com.hrms.domain.common.pojo.Result;
import com.hrms.domain.user.vo.OverviewPageVO;
import com.hrms.domain.user.vo.SalaryVO;
import com.hrms.domain.user.vo.WorkerDataVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Tag(name = "数据统计", description = "数据统计相关接口")
@Validated
@RequestMapping("/user/statistic")
public interface StatisticApi {


    @Operation(summary = "分页获取部门，用于绘制树状图")
    @GetMapping("/getAllDept")
    Result<List<Department>> getAllDept();

    @Operation(summary = "获取数据总览页的数据统计信息")
    @GetMapping("/getOverviewData")
    Result<OverviewPageVO> getOverviewData();

    @Operation(summary = "获取员工数据页的数据统计信息")
    @GetMapping("/getWorkerData")
    Result<WorkerDataVO> getWorkerData();

    @Operation(summary = "获取员工薪资变化统计信息")
    @GetMapping("/getWorkerSalaryData/{id}")
    Result<List<SalaryVO>> getWorkerSalaryData(@PathVariable @NotNull Integer id);
}
