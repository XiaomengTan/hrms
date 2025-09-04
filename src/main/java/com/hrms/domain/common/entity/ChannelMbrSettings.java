package com.hrms.domain.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("channel_mbr_settings")
public class ChannelMbrSettings {
    private Long id;
    private Long channelId;
    private Long memberId;
    private Long channelRole;
    private String customNickname;
    private Byte messageAuth;
    private Byte chatAuth;
    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    private LocalDateTime msgBacktracking;
}
