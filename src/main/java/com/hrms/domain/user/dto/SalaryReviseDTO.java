package com.hrms.domain.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalaryReviseDTO {
    @NotNull
    private Integer id;
    private Integer jobId;
    private Integer workerId;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate settlementTime;//在程序中添加
    private Integer cycle;
    private Double salaryOffset;
}
