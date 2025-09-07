package com.hrms.user.api;

import com.hrms.domain.common.dto.PageDataDTO;
import com.hrms.domain.common.dto.PageFullDataDTO;
import com.hrms.domain.common.entity.Lesson;
import com.hrms.domain.common.pojo.PageBean;
import com.hrms.domain.common.pojo.Result;
import com.hrms.domain.user.dto.LessonDTO;
import com.hrms.domain.user.dto.LessonReviseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "培训页面", description = "培训管理")
@RequestMapping("/user/lesson")
@Validated
public interface LessonApi {

    @Operation(summary = "新增培训课程")
    @PostMapping("/add")
    Result add(@RequestBody @Validated @NotNull LessonDTO lessonDTO);

    @Operation(summary = "删除培训课程")
    @DeleteMapping("/delete/{id}")
    Result delete(@PathVariable @NotNull Integer id);

    @Operation(summary = "修改课程信息")
    @PutMapping("/revise")
    Result<Lesson> revise(@RequestBody @Validated @NotNull LessonReviseDTO lessonReviseDTO);

    @Operation(summary = "分页查询课程信息")
    @GetMapping("/getByPage")
    Result<PageBean> getByPage(@ModelAttribute @Validated @NotNull PageDataDTO pageDataDTO);

    @Operation(summary = "根据id分页模糊搜索课程信息")
    @GetMapping("/getById")
    Result<PageBean> getById(@ModelAttribute @Validated @NotNull PageFullDataDTO pageFullDataDTO);

    @Operation(summary = "获取所有培训课程")
    @GetMapping("/getAll")
    Result<List<Lesson>> getAll();

}
