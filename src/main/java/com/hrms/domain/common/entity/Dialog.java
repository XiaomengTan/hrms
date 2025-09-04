package com.hrms.domain.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("dialog")
public class Dialog {
    private Long id;
    private Long userId;
    @NotNull
    private Byte entityType;
    @NotNull
    private Long entityId;
    private Long categoryId;
    private Long frontId;
    private Long backId;
}
