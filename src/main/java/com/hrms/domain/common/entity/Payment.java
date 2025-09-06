package com.hrms.domain.common.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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
    @JSONField(format = "yyyy-MM-dd")
//    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
}