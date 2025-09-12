package com.hrms.user.service;

import com.hrms.domain.user.vo.OverviewPageVO;
import com.hrms.domain.user.vo.SalaryVO;
import com.hrms.domain.user.vo.WorkerDataVO;

import java.util.List;

public interface StatisticService {
    /**
     * 获取数据总览页统计信息
     * @return
     */
    OverviewPageVO getOverviewData();

    /**
     * 获取员工统计页统计信息
     * @return
     */
    WorkerDataVO getWorkerData();

    /**
     * 获取员工薪资条信息
     * @return
     */
    List<SalaryVO> getWorkerSalaryData(Integer id);
}
