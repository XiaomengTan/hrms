package com.hrms.domain.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Salary
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("salary")
public class Salary {
    private Integer id;
    private Integer jobId;
    private Integer workerId;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate settlementTime;
    private Integer cycle;
    private Double salaryOffset;
    private Double salary;
}