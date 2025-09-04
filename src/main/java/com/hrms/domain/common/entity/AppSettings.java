package com.hrms.domain.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("app_settings")
public class AppSettings {
    @NotNull
    private Long id;
    private Byte backgroundColor;
    private Byte urlPreview;
    private Byte typingState;
    private Long alertSound;
    private Long specAlertSound;
    private Byte onlineState;
    private Byte findByEmail;
    private Byte findByNickname;
    private Byte findById;
    private Byte addTest;
    private Byte needVerify;
    @Pattern(regexp = "^.{1,64}")
    private String addQuestion;
}
