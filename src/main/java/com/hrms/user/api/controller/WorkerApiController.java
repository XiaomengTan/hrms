package com.hrms.user.api.controller;

import com.hrms.domain.common.dto.PageDataDTO;
import com.hrms.domain.common.dto.PageFullDataDTO;
import com.hrms.domain.common.entity.Job;
import com.hrms.domain.common.entity.Worker;
import com.hrms.domain.common.pojo.PageBean;
import com.hrms.domain.common.pojo.Result;
import com.hrms.domain.user.dto.WorkerDTO;
import com.hrms.domain.user.dto.WorkerReviseDTO;
import com.hrms.domain.user.pojo.WorkerSimplified;
import com.hrms.user.api.WorkerApi;
import com.hrms.user.service.WorkerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class WorkerApiController implements WorkerApi {
    @Autowired
    private WorkerService workerService;

    @Override
    public Result<String> addWorker(WorkerDTO workerDTO) {
        log.info("即将添加员工：{}", workerDTO);
        workerService.addWorker(workerDTO);
        log.info("员工添加成功");

        return Result.success("添加员工成功");
    }

    @Override
    public Result<String> deleteWorker(Integer id) {
        log.info("即将添加员工：{}", id);
        workerService.deleteById(id);
        log.info("员工删除成功");
        return Result.success("删除员工成功");
    }

    @Override
    public Result<String> deleteWorker(List<Integer> workerIds) {
        log.info("即将删除员工：{}", workerIds);
        workerService.deleteByIds(workerIds);
        log.info("批量删除员工成功");
        return Result.success("批量删除员工成功");
    }

    @Override
    public Result<Worker> reviseWorker(WorkerReviseDTO workerReviseDTO) {
        log.info("将修改员工内容为：{}", workerReviseDTO);
        Worker worker = workerService.reviseWorker(workerReviseDTO);
        log.info("员工修改成功");
        return Result.success(worker);
    }

    @Override
    public Result<PageBean> getByPage(PageDataDTO pageDataDTO) {
        log.info("分页查询员工：{}", pageDataDTO);
        PageBean result = workerService.getWorkerByPage(pageDataDTO);
        log.info("分页查询成功");
        return Result.success(result);
    }

    @Override
    public Result<PageBean> getById(PageFullDataDTO pageFullDataDTO) {
        log.info("按条件模糊分页查询员工：{}", pageFullDataDTO);
        PageBean result = workerService.getWorkerById(pageFullDataDTO);
        log.info("查询成功");
        return Result.success(result);
    }

    @Override
    public Result<Worker> getWorkerExact(Integer id) {
        log.info("根据" + id + "查询数据");
        Worker byId = workerService.getById(id);
        log.info("查询成功");
        return Result.success(byId);
    }

    @Override
    public Result<List<WorkerSimplified>> getAllWorkers() {
        log.info("查询所有员工");
        List<WorkerSimplified> workers = workerService.getAllWorker();
        log.info("查询成功");
        return Result.success(workers);
    }

    @Override
    public Result<PageBean> getByJob(PageFullDataDTO PageFullDataDTO) {
        log.info("根据岗位查询数据");
        PageBean result = workerService.getWorkerByJob(PageFullDataDTO);
        log.info("查询成功");
        return Result.success(result);
    }
}
