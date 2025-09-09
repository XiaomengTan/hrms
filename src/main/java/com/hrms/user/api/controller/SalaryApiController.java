package com.hrms.user.api.controller;

import com.hrms.domain.common.dto.PageDataDTO;
import com.hrms.domain.common.dto.PageFullDataDTO;
import com.hrms.domain.common.entity.Salary;
import com.hrms.domain.common.pojo.PageBean;
import com.hrms.domain.common.pojo.Result;
import com.hrms.domain.user.dto.SalaryDTO;
import com.hrms.domain.user.dto.SalaryReviseDTO;
import com.hrms.domain.user.vo.SalaryAddVO;
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
    public Result<Salary> addSalary(SalaryDTO salaryDTO) {
        log.info("添加薪资条：{}", salaryDTO);
        Salary salary = salaryService.addSalary(salaryDTO);
        log.info("添加薪资条成功");
        return Result.success(salary);
    }

    @Override
    public Result deleteSalary(Integer id) {
        log.info("删除薪资条：{}", id);
        salaryService.deleteById(id);
        log.info("删除薪资条成功");
        return Result.success("删除薪资条成功");
    }

    @Override
    public Result reviseSalary(SalaryReviseDTO salaryReviseDTO) {
        log.info("修改薪资条：{}", salaryReviseDTO);
        salaryService.reviseSalary(salaryReviseDTO);
        log.info("修改薪资条成功");
        return Result.success("删除薪资条成功");
    }

    @Override
    public Result<SalaryAddVO> getAddVO(Integer id) {
        log.info("查询被添加薪资员工的薪资相关数据：{}", id);
        SalaryAddVO salaryAddVO = salaryService.getAddVO(id);
        log.info("查询成功");
        return Result.success(salaryAddVO);
    }

    @Override
    public Result<PageBean> getByPage(PageDataDTO pageDataDTO) {
        log.info("查询薪资条：{}", pageDataDTO);
        PageBean pageBean = salaryService.getSalaryByPage(pageDataDTO);
        log.info("查询薪资条成功");
        return Result.success(pageBean);
    }

    @Override
    public Result<PageBean> getById(PageFullDataDTO pageFullDataDTO) {
        log.info("模糊搜索薪资条：{}", pageFullDataDTO);
        PageBean pageBean = salaryService.getSalaryById(pageFullDataDTO);
        log.info("模糊搜索成功");
        return Result.success(pageBean);
    }
}
