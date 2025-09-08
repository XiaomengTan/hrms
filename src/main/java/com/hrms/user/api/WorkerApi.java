package com.hrms.user.api;

import com.hrms.domain.common.dto.PageDataDTO;
import com.hrms.domain.common.dto.PageFullDataDTO;
import com.hrms.domain.common.entity.Worker;
import com.hrms.domain.common.pojo.PageBean;
import com.hrms.domain.common.pojo.Result;
import com.hrms.domain.user.dto.WorkerDTO;
import com.hrms.domain.user.dto.WorkerReviseDTO;
import com.hrms.domain.user.pojo.WorkerSimplified;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "员工管理", description = "员工接口")
@Validated
@RequestMapping("/user/worker")
public interface WorkerApi {

    @Operation(summary = "添加员工")
    @PostMapping("/add")
    Result<String> addWorker(@RequestBody @Validated @NotNull WorkerDTO workerDTO);

    @Operation(summary = "删除员工")
    @DeleteMapping("/delete/{id}")
    Result<String> deleteWorker(@PathVariable @NotNull Integer id);

    @Operation(summary = "批量删除员工")
    @DeleteMapping("/deleteBatch")
    Result<String> deleteWorker(@RequestBody @NotEmpty @NotNull List<Integer> workerIds);

    @Operation(summary = "修改员工信息")
    @PutMapping("/revise")
    Result<Worker> reviseWorker(@RequestBody @Validated @NotNull WorkerReviseDTO workerReviseDTO);

    @Operation(summary = "分页查询所有员工")
    @GetMapping("/getByPage")
    Result<PageBean> getByPage(@ModelAttribute @Validated @NotNull PageDataDTO pageDataDTO);

    @Operation(summary = "根据id或名称模糊查询员工")
    @GetMapping("/getById")
    Result<PageBean> getById(@ModelAttribute @Validated PageFullDataDTO pageFullDataDTO);

    @Operation(summary = "根据id精确查询员工")
    @GetMapping("/getExact/{id}")
    Result<Worker> getWorkerExact(@PathVariable @NotNull Integer id);

    @Operation(summary = "查询所有员工")
    @GetMapping("/getAll")
    Result<List<WorkerSimplified>> getAllWorkers();

    @Operation(summary = "根据岗位分页查询员工")
    @GetMapping("/getByJob")
    Result<PageBean> getByJob(@ModelAttribute @Validated PageFullDataDTO PageFullDataDTO);
}

