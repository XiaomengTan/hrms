package com.hrms.domain.user.vo;

import com.hrms.domain.user.pojo.PanelData;
import com.hrms.domain.user.pojo.SalaryDistribution;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OverviewPageVO {
    private PanelData panelData;
    private List<List<Long>> workerGrowthTrend;
    private SalaryDistribution salaryDistribution;
    private List<List<Double>> incomeExpenseTrend;
}
