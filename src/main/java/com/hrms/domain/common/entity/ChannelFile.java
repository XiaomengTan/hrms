package com.hrms.domain.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("channel_file")
public class ChannelFile {
    private Long id;
    private Long channelId;
    private Long fileId;
    private Long frontId;
    private Long backId;
}
