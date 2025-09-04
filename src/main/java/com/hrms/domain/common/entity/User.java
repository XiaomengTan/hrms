package com.hrms.domain.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("user")
public class User {
    @NotNull
    private Long id;//主键id
    @Email
    @Pattern(regexp = "^.{1,64}")
    private String email;
    private String password;
    @Pattern(regexp = "^.{1,32}")
    private String name;
    @Pattern(regexp = "^.{1,32}")
    private String phoneNum;
    @Pattern(regexp = "^.{1,32}")
    private String nickname;
    private Long avatar;
    @Pattern(regexp = "^.{1,64}")
    private String signature;
    private Long activityImg;
    private Long settingsId;
    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    private LocalDateTime createTime;
}
