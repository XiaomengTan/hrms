package com.hrms.domain.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("group_operations")
public class GroupOperations {
    private Long id;
    private Long defaultRole;
    private Long defaultChannels;
    private Byte inviteState;
    private Byte findId;
    private Byte findName;
    private Byte enterTest;
    private Byte needVerify;
    private String enterQuestion;
}
