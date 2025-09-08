package com.hrms.domain.user.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PanelData {
    private Long workerAmount;       // 员工总数
    private Long employedAmount;     // 在职员工数量
    private Long jobAmount;          // 岗位数量
    private Long departmentAmount;   // 部门数量
    private Long workerChange;       // 本月新增
}
