package com.hrms.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hrms.domain.common.dto.PageDataDTO;
import com.hrms.domain.common.entity.Job;
import com.hrms.domain.common.pojo.PageBean;
import com.hrms.domain.user.dto.JobGetByIdDTO;
import com.hrms.domain.user.dto.JobDTO;
import com.hrms.domain.user.dto.JobReviseDTO;
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
     * @param jobGetByIdDTO
     * @return
     */
    @Override
    public PageBean getJobById(JobGetByIdDTO jobGetByIdDTO) {
        //创建分页对象
        Page<Job> page = new Page<>(jobGetByIdDTO.getPageNum(), jobGetByIdDTO.getPageSize());

        //提取查询信息
        IPage<Job> jobIPage = jobMapper.getJobById(page, jobGetByIdDTO.getId(), jobGetByIdDTO.getName());

        //封装查询结果
        PageBean<Job> jobPageBean = new PageBean<>();
        jobPageBean.setTotal(jobIPage.getTotal());
        jobPageBean.setItems(jobIPage.getRecords());

        return jobPageBean;
    }

    /**
     * 仅分页查询数据，用于岗位页面初始值展示
     *
     * @param pageDataDTO
     * @return
     */
    @Override
    public PageBean getJobByPage(PageDataDTO pageDataDTO) {
        //创建分页对象
        Page<Job> page = new Page<>(pageDataDTO.getPageNum(), pageDataDTO.getPageSize());

        //提取查询信息
        IPage<Job> jobIPage = jobMapper.selectPage(page, null);

        //封装查询结果
        PageBean<Job> jobPageBean = new PageBean<>();
        jobPageBean.setTotal(jobIPage.getTotal());
        jobPageBean.setItems(jobIPage.getRecords());
        return jobPageBean;
    }

    /**
     * 修改岗位信息
     *
     * @param jobReviseDTO
     */
    @Override
    public Job reivseJob(JobReviseDTO jobReviseDTO) {
        //检查被修改的job是否存在于数据库中
        Job job = jobMapper.selectById(jobReviseDTO.getId());
        if (job == null) {
            throw new RuntimeException("被修改的数据不存在");
        }
        //属性拷贝
        Job newJob = new Job();
        BeanUtils.copyProperties(jobReviseDTO, newJob);
        //存在，则执行修改操作
        jobMapper.updateById(newJob);

        //返回修改结果
        return jobMapper.selectById(newJob.getId());
    }
}
