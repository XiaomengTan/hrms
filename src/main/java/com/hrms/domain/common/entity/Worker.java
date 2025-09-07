package com.hrms.domain.common.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDate;


/**
 * Worker
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("worker")
public class Worker {
    private Integer id;
    private String name;
    private Integer gender;
    private Integer degree;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
    private Integer socialSecurity;
    private String rpRecord;
    private String transfer;
    private Integer workStatus;
    private String certificate;
    private Integer laborStatus;
    private Integer job;
    private Integer department;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate onboardingTime;
    @TableField(insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate departureTime;
}
