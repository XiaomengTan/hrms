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
@TableName("notice")
public class Notice {
    private Long id;
    private Byte owner;
    private Long ownerId;
    private Long sender;
    private String title;
    private Long cover;
    private String content;
    private Byte accessAuth;
    private Byte sendEmail;
    private Byte hasFile;
    private Integer totalRead;
    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    private LocalDateTime postTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    private LocalDateTime updateTime;
}
