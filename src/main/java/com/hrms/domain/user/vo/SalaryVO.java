package com.hrms.domain.user.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalaryVO {
    private LocalDate settlementTime;
    private Double salary;

}
