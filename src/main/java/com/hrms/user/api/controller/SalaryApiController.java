package com.hrms.user.api.controller;

import com.hrms.domain.common.entity.Salary;
import com.hrms.domain.common.pojo.Result;
import com.hrms.user.api.SalaryApi;
import com.hrms.user.service.SalaryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class SalaryApiController implements SalaryApi {

    @Autowired
    private SalaryService salaryService;

    @Override
    public Result addSalary(Salary salary) {

        return null;
    }
}
