package com.hrms.domain.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("emoji")
public class Emoji {
    private Long id;
    private Long userId;
    @NotNull
    private Long fileId;
    private Long frontId;
    private Long backId;
}
