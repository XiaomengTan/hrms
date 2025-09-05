package com.hrms.user.api.controller;

import com.hrms.domain.common.dto.PageDataDTO;
import com.hrms.domain.common.dto.PageFullDataDTO;
import com.hrms.domain.common.entity.Job;
import com.hrms.domain.common.pojo.PageBean;
import com.hrms.domain.common.pojo.Result;
import com.hrms.domain.user.dto.JobDTO;
import com.hrms.domain.user.dto.JobReviseDTO;
import com.hrms.user.api.JobApi;
import com.hrms.user.service.JobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class JobApiController implements JobApi {

    @Autowired
    private JobService jobService;

    @Override
    public Result<String> addJob(JobDTO JobDTO) {
        log.info("添加岗位：", JobDTO.toString());
        jobService.addJob(JobDTO);
        log.info("添加成功");
        return Result.success("添加成功");
    }

    @Override
    public Result<String> deleteJob(Integer id) {
        log.info("删除岗位id为：", id);
        jobService.deleteById(id);
        log.info("删除成功");
        return Result.success("添加成功");
    }

    @Override
    public Result<PageBean> getJobById(PageFullDataDTO pageFullDataDTO) {
        log.info("根据id或名称查询岗位", pageFullDataDTO.toString());
        PageBean result = jobService.getJobById(pageFullDataDTO);
        log.info("查询成功");
        return Result.success(result);
    }

    @Override
    public Result<PageBean> getJobByPage(PageDataDTO pageDataDTO) {
        log.info("分页查询岗位", pageDataDTO.toString());
        PageBean result = jobService.getJobByPage(pageDataDTO);
        log.info("分页查询成功");
        return Result.success(result);
    }

    @Override
    public Result<Job> getJobExact(Integer id) {
        log.info("根据" + id + "查询数据");
        Job byId = jobService.getById(id);
        log.info("查询成功");
        return Result.success(byId);
    }

    @Override
    public Result<Job> reviseJob(JobReviseDTO jobReviseDTO) {
        log.info("修改岗位", jobReviseDTO.toString());
        Job job = jobService.reivseJob(jobReviseDTO);
        log.info("岗位修改成功");
        return Result.success(job);
    }
}
