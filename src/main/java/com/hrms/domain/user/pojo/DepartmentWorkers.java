package com.hrms.domain.user.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentWorkers {
    private String departmentName;
    private Long workerAmount;
}
