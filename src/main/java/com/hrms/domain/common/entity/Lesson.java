package com.hrms.domain.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Lesson
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("lesson")
public class Lesson {
    private Integer id;
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate schedule;
    private Integer timePlan;
    private String address;
    private String info;
}