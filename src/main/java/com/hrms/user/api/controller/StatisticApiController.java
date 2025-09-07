package com.hrms.user.api.controller;

import com.hrms.admin.service.DepartmentService;
import com.hrms.domain.common.dto.PageDataDTO;
import com.hrms.domain.common.entity.Department;
import com.hrms.domain.common.pojo.PageBean;
import com.hrms.domain.common.pojo.Result;
import com.hrms.user.api.StatisticApi;
import com.hrms.user.service.StatisticService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

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
}
