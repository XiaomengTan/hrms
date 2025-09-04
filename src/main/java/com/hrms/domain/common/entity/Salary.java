package com.hrms.domain.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * Salary
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("salary")
public class Salary {
    private Integer cycle;
    private Integer id;
    private Integer jobId;
    private Double salary;
    private Double salaryOffset;
    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    private LocalDateTime settlementTime;
    private Integer workerId;
}