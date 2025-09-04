package com.hrms.domain.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("channel_role")
public class ChannelRole {
    private Long id;
    @NotNull
    private Long channelId;
    private String name;
    private Long frontId;
    private Long backId;
    private Byte ctrlRole;
    private Byte editNotice;
    private Byte deleteNotice;
    private Byte editName;
    private Byte editIntroduction;
    private Byte editUser;
    private Byte disbandChannel;
    private Byte addUser;
    private Byte verifyAuth;
    private Byte log_visit;
}
