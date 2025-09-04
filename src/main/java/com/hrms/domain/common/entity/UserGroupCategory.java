package com.hrms.domain.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user_group_category")
public class UserGroupCategory {
    private Long id;
    private Long userId;
    @NotEmpty
    @Pattern(regexp = "^.{1,16}$")
    private String name;
    private Long frontId;
    private Long backId;

}
