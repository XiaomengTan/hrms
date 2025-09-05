package com.hrms.user.api;

import com.hrms.domain.common.entity.Worker;
import com.hrms.domain.common.pojo.Result;
import com.hrms.domain.user.dto.WorkerDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "员工管理", description = "员工接口")
@Validated
@RequestMapping("/user/worker")
public interface WorkerApi {

    @Operation(summary = "添加员工")
    @PostMapping("/add")
    Result<String> addWorker(@RequestBody @Validated @NotNull WorkerDTO workerDTO);

}

