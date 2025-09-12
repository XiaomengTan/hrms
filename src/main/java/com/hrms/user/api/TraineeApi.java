package com.hrms.user.api;

import com.hrms.domain.common.dto.PageDataDTO;
import com.hrms.domain.common.dto.PageFullDataDTO;
import com.hrms.domain.common.entity.Worker;
import com.hrms.domain.common.pojo.PageBean;
import com.hrms.domain.common.pojo.Result;
import com.hrms.domain.user.dto.TraineeDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "学员窗口", description = "课程学员管理")
@RequestMapping("/user/number")
@Validated
public interface TraineeApi {

    @Operation(summary = "批量添加学员")
    @PostMapping("/addBatch")
    Result addTrainees(@RequestBody @NotNull TraineeDTO traineeDTO);

    @Operation(summary = "批量删除学员")
    @DeleteMapping("/deleteBatch")
    Result deleteTrainees(@RequestBody @NotNull List<Integer> ids);

    @Operation(summary = "分页查询学员")
    @GetMapping("/getByPage")
    Result<PageBean> getTraineesByPage(@ModelAttribute @NotNull PageFullDataDTO pageFullDataDTO);

    @Operation(summary = "查询不在某课程的学员名单")
    @GetMapping("/notInLesson/{id}")
    Result<List<Worker>> getNotInLessonList(@PathVariable @NotNull Integer id);

    @Operation(summary = "查询在某课程的学员名单")
    @GetMapping("/inLesson/{id}")
    Result<List<Worker>> getInLessonList(@PathVariable @NotNull Integer id);

    @Operation(summary = "查询学员-课程映射表")
    @GetMapping("/getTraineeByPage")
    Result<PageBean> getAllTrainees(@ModelAttribute @NotNull PageDataDTO pageDataDTO);


}
