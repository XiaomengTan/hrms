package com.hrms.domain.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user_channel_settings")
public class UserChannelSettings {
    private Long id;
    @NotNull
    private Long userId;
    @NotNull
    private Long channelId;
    private Long categoryId;
    private Byte onlineState;
    private Byte alertState;
    private String customName;
}
