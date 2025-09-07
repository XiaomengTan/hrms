package com.hrms.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hrms.domain.common.dto.PageDataDTO;
import com.hrms.domain.common.dto.PageFullDataDTO;
import com.hrms.domain.common.entity.Job;
import com.hrms.domain.common.entity.Worker;
import com.hrms.domain.common.enums.Const;
import com.hrms.domain.common.pojo.PageBean;
import com.hrms.domain.user.dto.JobDTO;
import com.hrms.domain.user.dto.JobReviseDTO;
import com.hrms.user.mapper.JobMapper;
import com.hrms.user.mapper.WorkerMapper;
import com.hrms.user.service.JobService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class JobServiceImpl extends ServiceImpl<JobMapper, Job> implements JobService {

    @Autowired
    private JobMapper jobMapper;

    @Autowired
    private WorkerMapper workerMapper;

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
     * @param pageFullDataDTO
     * @return
     */
    @Override
    public PageBean getJobById(PageFullDataDTO pageFullDataDTO) {
        //创建分页对象
        Page<Job> page = new Page<>(pageFullDataDTO.getPageNum(), pageFullDataDTO.getPageSize());

        //提取查询信息
        IPage<Job> jobIPage = jobMapper.getJobById(page, pageFullDataDTO.getId(), pageFullDataDTO.getName());

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

    @Override
    @Transactional
    public void deleteById(Integer id) {
        //将所有岗位为当前被删除岗位id的员工的岗位id值设置为0
        workerMapper.update(new LambdaUpdateWrapper<Worker>()
                .eq(Worker::getJob, id)
                .set(Worker::getJob, Const.NO_ASSIGNED_JOB)
        );

        //删除该岗位
        jobMapper.deleteById(id);
    }

    /**
     * 查询所有岗位
     *
     * @return
     */
    @Override
    public List<Job> getAllJob() {
        List<Job> jobs = jobMapper.selectList(null);
        return jobs;
    }

    /**
     * 更新岗位在职总人数
     */
    @Override
    @Transactional
    public void totalInit() {
        //获取所有岗位的id
        List<Job> jobs = jobMapper.selectList(null);

        //遍历统计
        for(Job job : jobs) {
            //根据id查询worker
            List<Worker> workers = workerMapper.selectList(new LambdaQueryWrapper<Worker>()
                    .eq(Worker::getJob, job.getId())
                    //只统计任职状态的员工
                    .ne(Worker::getWorkStatus, Const.NO_ASSIGNED_JOB));
            Integer total = workers.size();
            job.setTotalWorker(total);
        }
        //更新数据
        jobMapper.updateById(jobs);
    }
}
