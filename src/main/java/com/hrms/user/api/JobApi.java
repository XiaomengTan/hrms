package com.hrms.user.api;

import com.hrms.domain.common.pojo.PageBean;
import com.hrms.domain.common.pojo.Result;
import com.hrms.domain.user.dto.JobByIdDTO;
import com.hrms.domain.user.dto.JobDTO;
import io.swagger.v3.oas.annotations.Operation;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "岗位页面", description = "岗位管理")
@RequestMapping("/user/job")
@Validated
public interface JobApi {

    @Operation(summary = "添加岗位")
    @PostMapping("/add")
    Result<String> addJob(@RequestBody @Validated @NotNull JobDTO jobDTO);

    @Operation(summary = "删除岗位")
    @DeleteMapping("/delete/{id}")
    Result<String> deleteJob(@PathVariable @NotNull Integer id);

    @Operation(summary = "根据id或岗位名称查询岗位")
    @GetMapping("/getById")
    Result<PageBean> getJobById(@ModelAttribute JobByIdDTO jobByIdDTO);
}
