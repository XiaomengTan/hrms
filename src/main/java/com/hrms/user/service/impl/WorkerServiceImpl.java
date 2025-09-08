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
import com.hrms.domain.user.dto.WorkerDTO;
import com.hrms.domain.user.dto.WorkerReviseDTO;
import com.hrms.domain.user.pojo.WorkerSimplified;
import com.hrms.user.mapper.JobMapper;
import com.hrms.user.mapper.WorkerMapper;
import com.hrms.user.service.WorkerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

@Service
public class WorkerServiceImpl extends ServiceImpl<WorkerMapper, Worker> implements WorkerService {
    @Autowired
    private WorkerMapper workerMapper;

    @Autowired
    private JobMapper jobMapper;

    /**
     * 添加员工
     *
     * @param workerDTO
     */
    @Override
    @Transactional
    public void addWorker(WorkerDTO workerDTO) {
        //检查该员工姓名是否已存在
        Worker workerInDb = workerMapper.selectByName(workerDTO.getName());
        //存在，说明添加重复了
        if (workerInDb != null) {
            throw new RuntimeException("重复添加员工");
        }

        // 添加的员工必须是在职的
        if (workerDTO.getWorkStatus() == Const.NO_ASSIGNED_JOB) {
            //此为错误操作
            throw new RuntimeException("新增的员工必须为在职员工");
        }

        //属性拷贝
        Worker worker = new Worker();
        worker.setDepartureTime(Const.NOT_RESIGNED_DATA);
        BeanUtils.copyProperties(workerDTO, worker);

        //将员工添加到员工表
        workerMapper.insert(worker);

        //将对应岗位的员工总数加一
        Job job = new Job();
        Integer jobId = worker.getJob();
        job.setId(jobId);
        Job jobInDb = jobMapper.selectById(jobId);
        if (jobInDb == null) {
            throw new RuntimeException("员工所属的岗位不存在");
        }
        //岗位存在，则将岗位员工数加一并更新岗位数据
        job.setTotalWorker(jobInDb.getTotalWorker() + 1);
        jobMapper.updateById(job);

        //完成
    }

    /**
     * 删除员工
     *
     * @param id
     */
    @Override
    @Transactional
    public void deleteById(Integer id) {
        //检查要删除的员工数据是否存在
        Worker worker = workerMapper.selectById(id);
        if (worker == null) {
            throw new RuntimeException("要删除的员工不存在");
        }
        //检查该员工有无离职
        if (worker.getWorkStatus() != Const.NO_ASSIGNED_JOB) {
            //没有离职，则需要将岗位总人数减一
            //检查员工的岗位是否存在
            Integer jobId = worker.getJob();
            Job job = jobMapper.selectById(jobId);
            if (job == null) {
                throw new RuntimeException("该员工的职业异常，其所属的岗位不存在");
            }
            //将对应岗位的totalWorker减一
            job.setTotalWorker(job.getTotalWorker() - 1);
            //更新job数据
            jobMapper.updateById(job);
        }
        //已离职，直接删除
        //删除员工
        workerMapper.deleteById(id);
    }

    /**
     * 批量删除员工
     *
     * @param workerIds
     */
    @Override
    @Transactional
    public void deleteByIds(List<Integer> workerIds) {
        //获取员工列表
        List<Worker> workers = workerMapper.selectBatchIds(workerIds);
        //建立哈希表，键为jobid，值为删除了的员工总数
        Map<Integer, Integer> offJobs = new HashMap<>();
        for (Worker worker : workers) {
            //检查该员工是否离职
            if (worker.getWorkStatus() == Const.NO_ASSIGNED_JOB) {
                //离职员工跳过对职业总数的修改操作
                continue;
            }
            //得到职业id
            Integer jobId = worker.getJob();
            //检查当前职位是否在哈希表中存在
            if (offJobs.containsKey(jobId)) {
                //存在，则将值加一
                Integer jobTotal = offJobs.get(jobId) + 1;
                offJobs.put(jobId, jobTotal);
            } else {
                //不存在，说明是首次添加
                offJobs.put(worker.getJob(), 1);
            }
        }

        //批量删除员工
        workerMapper.deleteByIds(workerIds);

        //批量将职位的员工总数减去对应的员工数量
        for (Integer jobId : offJobs.keySet()) {
            //获取当前职业的原有员工总数
            Job jobInDb = jobMapper.selectById(jobId);
            Integer totalWorker = jobInDb.getTotalWorker();
            //创建更新对象
            Job job = new Job();
            job.setId(jobId);
            //将员工总数减去对应的被删去了的员工数量
            job.setTotalWorker(totalWorker - offJobs.get(jobId));
            //更新数据库
            jobMapper.updateById(job);
        }
    }

    /**
     * 修改员工
     *
     * @param workerReviseDTO
     * @return
     */
    @Override
    @Transactional
    public Worker reviseWorker(WorkerReviseDTO workerReviseDTO) {
        //检查要修改的内容是否存在
        Worker worker = workerMapper.selectById(workerReviseDTO.getId());

        //检查要修改的员工是否存在
        if (worker == null) {
            throw new RuntimeException("原有岗位不存在");
        }

        Integer workStatus = worker.getWorkStatus();
        Integer newStatus = workerReviseDTO.getWorkStatus();
        //检查是否有修改员工岗位
        Integer jobId = workerReviseDTO.getJob();
        if (jobId != null && workStatus != Const.NO_ASSIGNED_JOB) {
            //如果有且该员工未离职，需要将原岗位员工总数减一，新岗位员工总数加一
            Integer oldJobId = worker.getJob();
            Job oldJob = jobMapper.selectById(oldJobId);
            jobMapper.update(new LambdaUpdateWrapper<Job>()
                    .eq(Job::getId, oldJobId)
                    .set(Job::getTotalWorker, oldJob.getTotalWorker() - 1));
            //将新岗位job的total加一
            Job newJob = jobMapper.selectById(jobId);
            newJob.setTotalWorker(newJob.getTotalWorker() + 1);
            jobMapper.updateById(newJob);
        }

        //确保更改的是新岗位的job total
        Integer realJobId = jobId != null ? jobId : worker.getJob();
        //检查员工的任职状态是否有修改
        if (!Objects.equals(workStatus, newStatus)) {
            //有修改，则判断是否改为离职状态
            if (newStatus == Const.NO_ASSIGNED_JOB) {
                //在职变为了离职，岗位通过三元运算符判断而来，将最新岗位总数减一
                Job job = jobMapper.selectById(realJobId);
                job.setTotalWorker(job.getTotalWorker() - 1);
                jobMapper.updateById(job);
                //设定离职时间为现在
                workerReviseDTO.setDepartureTime(LocalDate.now());
            } else {
                //离职变为了在职，岗位通过三元运算符判断而来，将最新岗位总数加一
                Job job = jobMapper.selectById(realJobId);
                job.setTotalWorker(job.getTotalWorker() + 1);
                jobMapper.updateById(job);
                //设定离职时间为null，必须直接设置worker才能生效
                workerReviseDTO.setDepartureTime(Const.NOT_RESIGNED_DATA);
            }
        }
        //属性拷贝
        BeanUtils.copyProperties(workerReviseDTO, worker);
        workerMapper.updateById(worker);

        //将修改后的员工数据返回
        return workerMapper.selectById(worker.getId());
    }

    /**
     * 分页查询员工
     *
     * @param pageDataDTO
     * @return
     */
    @Override
    public PageBean getWorkerByPage(PageDataDTO pageDataDTO) {
        //创建分页对象
        Page<Worker> page = new Page<>(pageDataDTO.getPageNum(), pageDataDTO.getPageSize());

        //提取查询信息
        IPage<Worker> workerIPage = workerMapper.selectPage(page, null);

        //封装查询结果
        PageBean<Worker> workerPageBean = new PageBean<>();
        workerPageBean.setTotal(workerIPage.getTotal());
        workerPageBean.setItems(workerIPage.getRecords());
        return workerPageBean;
    }

    /**
     * 按条件模糊分页查询员工
     *
     * @param pageFullDataDTO
     * @return
     */
    @Override
    public PageBean getWorkerById(PageFullDataDTO pageFullDataDTO) {
        //创建分页对象
        Page<Worker> page = new Page<>(pageFullDataDTO.getPageNum(), pageFullDataDTO.getPageSize());
        //封装查询结果
        IPage<Worker> workerIPage = workerMapper.getWorkerById(page, pageFullDataDTO.getId(), pageFullDataDTO.getName());
        //封装查询结果
        PageBean<Worker> workerPageBean = new PageBean<>();
        workerPageBean.setTotal(workerIPage.getTotal());
        workerPageBean.setItems(workerIPage.getRecords());
        return workerPageBean;
    }

    /**
     * 根据岗位查询数据，需注意此接口请求的name为null
     *
     * @param pageFullDataDTO
     * @return
     */
    @Override
    public PageBean getWorkerByJob(PageFullDataDTO pageFullDataDTO) {
        //创建分页对象
        Page<Worker> page = new Page<>(pageFullDataDTO.getPageNum(), pageFullDataDTO.getPageSize());
        //封装查询结果
        IPage<Worker> workerIPage = workerMapper.getWorkerByJob(page, pageFullDataDTO.getId());
        //封装查询结果
        PageBean<Worker> workerPageBean = new PageBean<>();
        workerPageBean.setTotal(workerIPage.getTotal());
        workerPageBean.setItems(workerIPage.getRecords());
        return workerPageBean;
    }

    /**
     * 获取员工列表
     *
     * @return
     */
    @Override
    public List<WorkerSimplified> getAllWorker() {
        List<Worker> workers = workerMapper.selectList(new LambdaQueryWrapper<Worker>()
                .select(Worker::getId, Worker::getName));
        List<WorkerSimplified> workerSimplifiedList = new ArrayList<>();
        for (Worker worker : workers) {
            WorkerSimplified workerSimplified = new WorkerSimplified();
            BeanUtils.copyProperties(worker, workerSimplified);
            workerSimplifiedList.add(workerSimplified);
        }
        return workerSimplifiedList;
    }
}
