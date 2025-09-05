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
public class SalaryDTO {
    @NotNull
    private Integer jobId;
    @NotNull
    private Integer workerId;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate settlementTime;//在程序中添加
    @NotNull
    private Integer cycle;
    private Double salaryOffset;
    //无salary，因salary根据jobSalary和前述salaryoffset算出，不可由操作人员添加
}
