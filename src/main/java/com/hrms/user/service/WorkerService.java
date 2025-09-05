package com.hrms.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hrms.domain.common.dto.PageDataDTO;
import com.hrms.domain.common.dto.PageFullDataDTO;
import com.hrms.domain.common.entity.Worker;
import com.hrms.domain.common.pojo.PageBean;
import com.hrms.domain.user.dto.WorkerDTO;
import com.hrms.domain.user.dto.WorkerReviseDTO;

import java.util.List;

public interface WorkerService extends IService<Worker> {
    /**
     * 添加员工
     * @param workerDTO
     */
    void addWorker(WorkerDTO workerDTO);

    /**
     * 删除员工
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 批量删除员工
     * @param workerIds
     */
    void deleteByIds(List<Integer> workerIds);

    /**
     * 修改员工
     * @param workerReviseDTO
     * @return
     */
    Worker reviseWorker(WorkerReviseDTO workerReviseDTO);

    /**
     * 分页查询员工
     * @param pageDataDTO
     * @return
     */
    PageBean getWorkerByPage(PageDataDTO pageDataDTO);


    /**
     * 按条件模糊分页查询员工
     * @param pageFullDataDTO
     * @return
     */
    PageBean getWorkerById(PageFullDataDTO pageFullDataDTO);

    /**
     * 根据岗位查询数据，需注意此接口请求的name为null
     * @param pageFullDataDTO
     * @return
     */
    PageBean getWorkerByJob(PageFullDataDTO pageFullDataDTO);
}
