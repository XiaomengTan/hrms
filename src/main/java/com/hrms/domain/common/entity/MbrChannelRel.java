package com.hrms.domain.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("mbr_channel_rel")
public class MbrChannelRel {
    private Long id;
    private Long groupId;
    private Long memberId;
    private Long channelId;
}
