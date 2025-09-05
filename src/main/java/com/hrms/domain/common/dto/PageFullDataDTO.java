package com.hrms.domain.common.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageFullDataDTO {
    private Integer id;
    @Size(min = 1, max = 64)
    private String name;
    @NotNull
    @Range(min = 1, max = 999)
    private Integer pageNum;
    @NotNull
    @Range(min = 5, max = 50)
    private Integer pageSize;
}
