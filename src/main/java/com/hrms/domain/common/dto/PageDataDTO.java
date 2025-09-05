package com.hrms.domain.common.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageDataDTO {
    @NotNull
    @Range(min = 1, max = 999)
    private Integer pageNum;
    @NotNull
    @Range(min = 5, max = 10)
    private Integer pageSize;
}
