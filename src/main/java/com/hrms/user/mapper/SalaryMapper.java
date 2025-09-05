package com.hrms.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hrms.domain.common.entity.Salary;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SalaryMapper extends BaseMapper<Salary> {
    IPage<Salary> getSalaryById(Page<Salary> salaryPage, Integer id, String name);
}
