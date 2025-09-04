package com.hrms.domain.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user_channel_category")
public class UserChannelCategory {
    private Long id;
    private Long userId;
    private Long groupId;
    private String name;
    private Long frontId;
    private Long backId;
}
