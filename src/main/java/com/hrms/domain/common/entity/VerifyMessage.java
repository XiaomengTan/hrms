package com.hrms.domain.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("verify_message")
public class VerifyMessage {
    private Long id;
    @NotEmpty
    @Pattern(regexp = "^(user|group)$")
    private String senderType;
    @NotNull
    private Long senderId;
    @Pattern(regexp = "^(user|group|channel)$")
    private String receiverType;
    @NotNull
    private Long receiverId;
    private String verifyContent;
    private String verifyReply;
    private Byte verifyState;
    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    private LocalDateTime finishTime;
}
