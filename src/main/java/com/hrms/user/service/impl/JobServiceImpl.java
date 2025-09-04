package com.hrms.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hrms.domain.common.entity.Job;
import com.hrms.domain.common.pojo.PageBean;
import com.hrms.domain.user.dto.JobByIdDTO;
import com.hrms.domain.user.dto.JobDTO;
import com.hrms.user.mapper.JobMapper;
import com.hrms.user.service.JobService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobServiceImpl extends ServiceImpl<JobMapper, Job> implements JobService {

    @Autowired
    private JobMapper jobMapper;

    @Override
    public void addJob(JobDTO jobDTO) {
        //逻辑性校验
        //检查数据库中是否存在该职位
        Job jobInDB = jobMapper.selectByName(jobDTO.getName());
        if (jobInDB != null) {
            throw new RuntimeException("该岗位已存在");
        }
        //数据拷贝到entity类中
        Job newJob = new Job();
        BeanUtils.copyProperties(jobDTO, newJob);
        newJob.setTotalWorker(0);
        //插入数据
        jobMapper.insert(newJob);
    }

    /**
     * 根据id和name分页查询岗位信息
     *
     * @param jobByIdDTO
     * @return
     */
    @Override
    public PageBean getJobById(JobByIdDTO jobByIdDTO) {
        //创建分页对象
        Page<Job> page = new Page<>(jobByIdDTO.getPageNum(), jobByIdDTO.getPageSize());

        //提取查询信息
        IPage<Job> jobIPage = jobMapper.getJobById(page, jobByIdDTO.getId(), jobByIdDTO.getName());

        //封装查询结果
        PageBean<Job> jobPageBean = new PageBean<>();
        jobPageBean.setTotal(jobIPage.getTotal());
        jobPageBean.setItems(jobIPage.getRecords());

        return jobPageBean;
    }
}
