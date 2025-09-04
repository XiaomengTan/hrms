package com.hrms.domain.user.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobDTO {
    @NotNull
    @Size(min = 2, max = 64)
    private String name;
    @NotNull
    @Size(min = 1, max = 1024)
    private String demand;
    @NotNull
    @Range(min = 1, max = 3)
    private Integer type;
    @NotNull
    @Range(min = 2000, max = 60000)
    private Integer salary;
    @NotNull
    @Range(min = 20, max = 100)
    private Integer overtimePay;
    @NotNull
    @Size(min = 1, max = 1024)
    private String introduction;
}
