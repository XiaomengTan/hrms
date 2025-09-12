package com.hrms.user.api.controller;

import com.hrms.admin.service.DepartmentService;
import com.hrms.domain.common.entity.Department;
import com.hrms.domain.common.pojo.Result;
import com.hrms.domain.user.vo.OverviewPageVO;
import com.hrms.domain.user.vo.SalaryVO;
import com.hrms.domain.user.vo.WorkerDataVO;
import com.hrms.user.api.StatisticApi;
import com.hrms.user.service.StatisticService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class StatisticApiController implements StatisticApi {

    @Autowired
    private StatisticService statisticService;

    @Autowired
    private DepartmentService departmentService;


    @Override
    public Result<List<Department>> getAllDept() {
        log.info("即将分页获取部门信息");
        List<Department> departments = departmentService.getAllDept();
        log.info("分页查询部门成功");
        return Result.success(departments);
    }

    @Override
    public Result<OverviewPageVO> getOverviewData() {
        log.info("获取数据总览页数据信息");
        OverviewPageVO overviewPageVO = statisticService.getOverviewData();
        log.info("获取成功");
        return Result.success(overviewPageVO);
    }

    @Override
    public Result<WorkerDataVO> getWorkerData() {
        log.info("获取员工统计页数据信息");
        WorkerDataVO workerDataVO = statisticService.getWorkerData();
        log.info("获取成功");
        return Result.success(workerDataVO);
    }

    @Override
    public Result<List<SalaryVO>> getWorkerSalaryData(Integer id) {
        log.info("获取员工薪资变化统计信息");
        List<SalaryVO> salaries = statisticService.getWorkerSalaryData(id);
        log.info("获取成功");
        return Result.success(salaries);
    }
}
