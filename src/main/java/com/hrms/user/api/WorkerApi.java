package com.hrms.user.api;

import com.hrms.domain.common.pojo.Result;
import com.hrms.domain.common.entity.Activity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@Tag(name = "动态管理", description = "动态相关操作接口")
@RequestMapping("/user/activity")
public interface WorkerApi {

    @Operation(summary = "发布动态")
    @PostMapping
    Result postActivity(@Validated @RequestBody @NotNull Activity activity);

    @Operation(summary = "删除动态")
    @DeleteMapping
    Result deleteActivity(@NotNull @RequestParam Long activityId);

    @Operation(summary = "点赞动态或取消点赞")
    @PatchMapping
    Result altActivityAgreeNum(@NotNull @RequestParam Long activityId);//optimize考虑修改参数为成activityId finish

    @Operation(summary = "更新一条动态")
    @PutMapping
    Result updateActivity(@Validated @NotNull @RequestBody Activity activity);

    @Operation(summary = "获取用户的动态列表")
    @GetMapping("/user/list")
    Result getUserActivityList();

    @Operation(summary = "获取好友的动态列表")
    @GetMapping("/friend/list")
    Result getFriendActivityList(@RequestParam @NotNull Long friendId);

    @Operation(summary = "获取一条动态详细信息")
    @GetMapping("/info")
    Result getActivityInfo(@RequestParam @NotNull Long activityId);
}

