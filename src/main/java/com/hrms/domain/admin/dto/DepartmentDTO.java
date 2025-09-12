package com.hrms.domain.admin.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDTO {
    @NotNull
    @Size(min = 2, max = 64)
    private String name;//部门名称
    private Integer supervisor;//部门主管
    @NotNull
    private Integer uperId;//上级部门id，没有则为0，为根节点
}
