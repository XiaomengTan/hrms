package com.hrms.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hrms.domain.common.entity.Worker;
import com.hrms.user.mapper.WorkerMapper;
import com.hrms.user.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkerServiceImpl extends ServiceImpl<WorkerMapper, Worker> implements WorkerService {
    @Autowired
    private WorkerMapper workerMapper;
}
