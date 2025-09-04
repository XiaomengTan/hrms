package com.hrms.domain.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Lesson
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("lesson")
public class Lesson {
    private String address;
    private Long id;
    private String info;
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    private LocalDateTime schedule;
    private String timePlan;
}