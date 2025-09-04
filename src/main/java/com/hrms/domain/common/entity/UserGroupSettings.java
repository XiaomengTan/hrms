package com.hrms.domain.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user_group_settings")
public class UserGroupSettings {
    private Long id;
    private Long userId;
    private Long groupId;
    private Long categoryId;
    private Byte onlineAuth;
    private Byte alertState;
    private String customName;
}
