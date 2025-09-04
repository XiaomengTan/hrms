package com.hrms.user.api.controller;

import com.hrms.domain.common.pojo.PageBean;
import com.hrms.domain.common.pojo.Result;
import com.hrms.domain.user.dto.JobByIdDTO;
import com.hrms.domain.user.dto.JobDTO;
import com.hrms.user.api.JobApi;
import com.hrms.user.service.JobService;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
        jobService.removeById(id);
        log.info("删除成功");
        return Result.success("添加成功");
    }

    @Override
    public Result<PageBean> getJobById(JobByIdDTO jobByIdDTO) {
        log.info("根据id或名称查询岗位", jobByIdDTO.toString());
        PageBean result = jobService.getJobById(jobByIdDTO);
        log.info("查询成功");
        return Result.success(result);
    }
}
