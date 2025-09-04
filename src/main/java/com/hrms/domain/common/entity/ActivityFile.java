package com.hrms.domain.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("activity_file")
public class ActivityFile {
    @NotNull
    private Long id;
    @NotNull
    private Long activityId;
    @NotNull
    private Long fileId;
    private Long frontId;
    private Long backId;
}
