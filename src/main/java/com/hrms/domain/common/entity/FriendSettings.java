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
@TableName("friend_settings")
public class FriendSettings {
    private Long id;
    private Long userId;
    private Long friendId;
    private Long categoryId;
    private String remarkName;
    private String description;
    private Byte chatAuth;
    private Byte alertState;
    private Byte getActivity;
    private Byte getOnline;
    private Byte getUsername;
    private Byte getPhone;
    private Byte typingState;
    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    private LocalDateTime addTime;
}
