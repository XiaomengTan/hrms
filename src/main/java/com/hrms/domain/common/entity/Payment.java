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
    private Double profit;
    private Double tax;
    private Double socialSecurity;
    private Double water;
    private Double electricity;
    private Double salary;
    private Double outlay;
    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    private LocalDateTime date;



}