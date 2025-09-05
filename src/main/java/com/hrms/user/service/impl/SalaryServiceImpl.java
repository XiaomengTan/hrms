package com.hrms.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hrms.domain.common.entity.Salary;
import com.hrms.user.mapper.SalaryMapper;
import com.hrms.user.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalaryServiceImpl extends ServiceImpl<SalaryMapper, Salary> implements SalaryService {
    @Autowired
    private SalaryMapper salaryMapper;

}
