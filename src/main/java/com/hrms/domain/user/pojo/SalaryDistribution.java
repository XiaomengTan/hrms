package com.hrms.domain.user.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalaryDistribution {
    private Long more;   // 高薪资段人数
    private Long high;   // 较高薪资段人数
    private Long middle; // 中等薪资段人数
    private Long low;    // 较低薪资段人数
    private Long few;    // 低薪资段人数
}
