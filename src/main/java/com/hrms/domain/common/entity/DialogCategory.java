package com.hrms.domain.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("dialog_category")
public class DialogCategory {
    private Long id;
    private Long userId;
    @Pattern(regexp = "^.{1,16}$")
    private String name;
}
