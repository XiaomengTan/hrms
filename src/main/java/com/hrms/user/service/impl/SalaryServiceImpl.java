package com.hrms.user.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hrms.annotation.Log;
import com.hrms.domain.common.dto.PageDataDTO;
import com.hrms.domain.common.dto.PageFullDataDTO;
import com.hrms.domain.common.entity.Job;
import com.hrms.domain.common.entity.Salary;
import com.hrms.domain.common.pojo.PageBean;
import com.hrms.domain.user.dto.SalaryDTO;
import com.hrms.domain.user.dto.SalaryReviseDTO;
import com.hrms.user.mapper.JobMapper;
import com.hrms.user.mapper.SalaryMapper;
import com.hrms.user.service.SalaryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class SalaryServiceImpl extends ServiceImpl<SalaryMapper, Salary> implements SalaryService {
    @Autowired
    private SalaryMapper salaryMapper;

    @Autowired
    private JobMapper jobMapper;

    /**
     * 添加薪资条
     *
     * @param salaryDTO
     * @return
     */
    @Log
    @Override
    @Transactional
    public Salary addSalary(SalaryDTO salaryDTO) {
        //根据jobid查询基础薪资
        Integer jobId = salaryDTO.getJobId();
        Job job = jobMapper.selectById(jobId);
        //判断job是否为null
        if (job == null) {
            throw new RuntimeException("工作不存在");
        }
        Integer baseSalary = job.getSalary();

        //加上薪资的变化
        Double realSalary = baseSalary + salaryDTO.getSalaryOffset();

        //teleport还需要在此修改本日的支出情形

        //属性拷贝
        Salary salary = new Salary();
        BeanUtils.copyProperties(salaryDTO, salary);

        //添加时间
        salary.setSalary(realSalary);
        salary.setSettlementTime(LocalDate.now());

        //存入数据库
        salaryMapper.insert(salary);

        return salary;
    }

    /**
     * 删除薪资条
     *
     * @param id
     */
    @Log
    @Override
    @Transactional
    public void deleteById(Integer id) {
        //teleport删除前，首先需要复原今日的流水

        //删除薪资条
        salaryMapper.deleteById(id);
    }

    /**
     * 修改薪资条
     *
     * @param salaryReviseDTO
     */
    @Log
    @Override
    @Transactional
    public void reviseSalary(SalaryReviseDTO salaryReviseDTO) {
        //创建修改后的Salary对象
        Salary salary = new Salary();
        //检查有无修改salaryOffset
        Double salaryOffset = salaryReviseDTO.getSalaryOffset();
        //获取需要修改的薪资单
        Salary reviseSalary = salaryMapper.selectById(salaryReviseDTO.getId());
        if (salaryOffset != null) {
            //重新计算总工资
            Integer jobId = reviseSalary.getJobId();
            Job job = jobMapper.selectById(jobId);
            //查询该岗位的基础工资
            Integer jobSalary = job.getSalary();
            //将工资条的总工资设置为新值
            salary.setSalary(jobSalary + salaryOffset);
        }
        //属性拷贝
        BeanUtils.copyProperties(salaryReviseDTO, salary);
        //设置修改时间
        salary.setSettlementTime(LocalDate.now());
        //存入数据库中
        salaryMapper.updateById(salary);
        //teleport还需更改日流水，暂略
    }

    /**
     * 分页查询薪资
     *
     * @param pageDataDTO
     * @return
     */
    @Log
    @Override
    public PageBean getSalaryByPage(PageDataDTO pageDataDTO) {
        Page<Salary> salaryPage = new Page<>(pageDataDTO.getPageNum(), pageDataDTO.getPageSize());

        IPage<Salary> salaryIPage = salaryMapper.selectPage(salaryPage, null);

        PageBean<Salary> salaryPageBean = new PageBean<>();
        salaryPageBean.setTotal(salaryIPage.getTotal());
        salaryPageBean.setItems(salaryIPage.getRecords());
        return salaryPageBean;
    }

    /**
     * 模糊搜索薪资条
     *
     * @param pageFullDataDTO
     * @return
     */
    @Log
    @Override
    public PageBean getSalaryById(PageFullDataDTO pageFullDataDTO) {
        Page<Salary> salaryPage = new Page<>(pageFullDataDTO.getPageNum(), pageFullDataDTO.getPageSize());

        IPage<Salary> salaryIPage = salaryMapper.getSalaryById(salaryPage, pageFullDataDTO.getId(), pageFullDataDTO.getName());

        PageBean<Salary> salaryPageBean = new PageBean<>();
        salaryPageBean.setTotal(salaryIPage.getTotal());
        salaryPageBean.setItems(salaryIPage.getRecords());
        return salaryPageBean;
    }
}
