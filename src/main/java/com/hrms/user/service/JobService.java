package com.hrms.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hrms.domain.common.dto.PageDataDTO;
import com.hrms.domain.common.dto.PageFullDataDTO;
import com.hrms.domain.common.entity.Job;
import com.hrms.domain.common.pojo.PageBean;
import com.hrms.domain.user.dto.JobDTO;
import com.hrms.domain.user.dto.JobReviseDTO;

public interface JobService extends IService<Job> {
    /**
     * 添加岗位
     * @param jobDTO
     */
    void addJob(JobDTO jobDTO);

    /**
     * 根据id和name分页查询岗位信息
     * @param pageFullDataDTO
     * @return
     */
    PageBean getJobById(PageFullDataDTO pageFullDataDTO);

    /**
     * 仅分页查询数据，用于岗位页面初始值展示
     * @param pageDataDTO
     * @return
     */
    PageBean getJobByPage(PageDataDTO pageDataDTO);

    /**
     * 修改岗位信息
     * @param jobReviseDTO
     */
    Job reivseJob(JobReviseDTO jobReviseDTO);

    /**
     * 删除岗位
     * @param id
     */
    void deleteById(Integer id);
}
