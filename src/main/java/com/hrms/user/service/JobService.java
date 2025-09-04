package com.hrms.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hrms.domain.common.entity.Job;
import com.hrms.domain.common.pojo.PageBean;
import com.hrms.domain.user.dto.JobByIdDTO;
import com.hrms.domain.user.dto.JobDTO;

public interface JobService extends IService<Job> {
    /**
     * 添加岗位
     * @param jobDTO
     */
    void addJob(JobDTO jobDTO);

    /**
     * 根据id和name分页查询岗位信息
     * @param jobByIdDTO
     * @return
     */
    PageBean getJobById(JobByIdDTO jobByIdDTO);
}
