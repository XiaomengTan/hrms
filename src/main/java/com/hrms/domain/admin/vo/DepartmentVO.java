package com.hrms.domain.admin.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentVO {
    private Integer id;
    private String name;//部门名称
    private Integer supervisor;//部门主管
    private DepartmentVO uperDept;//上级部门id，没有则为0，为根节点
}
