package com.hrms.domain.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * Payment
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("payment")
public class Payment {
    private Integer id;
    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    private LocalDateTime date;
    private Double electricity;
    private Double outlay;
    private Double profit;
    private Double salary;
    private Double socialSecurity;
    private Double tax;
    private Double water;
}