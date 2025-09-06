package com.hrms.domain.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("department")
public class Department {
    private Integer id;
    private String name;//部门名称
    private Integer supervisor;//部门主管
    private Integer uperId;//上级部门id，没有则为0，为根节点
}
