package com.hrms.domain.admin.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {
    @NotNull
    private Integer id;
    private Double profit;
    private Double tax;
    private Double socialSecurity;
    private Double water;
    private Double electricity;
    private Double outlay;
    //日期不允许修改
    //薪资需在薪资管理位置修改，不可手动添加数值
}
