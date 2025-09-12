package com.hrms.user.api;

import com.hrms.domain.common.dto.PageDataDTO;
import com.hrms.domain.common.dto.PageFullDataDTO;
import com.hrms.domain.common.entity.Job;
import com.hrms.domain.common.pojo.PageBean;
import com.hrms.domain.common.pojo.Result;
import com.hrms.domain.user.dto.JobDTO;
import com.hrms.domain.user.dto.JobReviseDTO;
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

    @Operation(summary = "修改岗位")
    @PutMapping("/revise")
    Result<Job> reviseJob(@RequestBody @NotNull @Validated JobReviseDTO jobReviseDTO);

    @Operation(summary = "根据id或岗位名称查询岗位")
    @GetMapping("/getById")
    Result<PageBean> getJobById(@ModelAttribute @Validated PageFullDataDTO pageFullDataDTO);

    @Operation(summary = "仅分页查询岗位")
    @GetMapping("/getByPage")
    Result<PageBean> getJobByPage(@ModelAttribute @NotNull @Validated PageDataDTO pageDataDTO);

    @Operation(summary = "只根据id精确查询")
    @GetMapping("/getExact/{id}")
    Result<Job> getJobExact(@PathVariable @NotNull Integer id);

    @Operation(summary = "查询所有岗位")
    @GetMapping("/getAll")
    Result<List<Job>> getAllJob();

    @Operation(summary = "更新岗位总人数")
    @PutMapping("/totalInit")
    Result totalInit();


}
