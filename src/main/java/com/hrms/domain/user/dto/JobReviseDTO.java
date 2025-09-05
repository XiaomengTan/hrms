package com.hrms.domain.user.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobReviseDTO {
    @NotNull
    private Integer id;
    @Size(min = 2, max = 64)
    private String name;
    @Size(min = 1, max = 1024)
    private String demand;
    @Range(min = 1, max = 3)
    private Integer type;
    @Range(min = 2000, max = 60000)
    private Integer salary;
    @Range(min = 20, max = 100)
    private Integer overtimePay;
    @Size(min = 1, max = 1024)
    private String introduction;
}
