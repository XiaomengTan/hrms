package com.hrms.user.api.controller;

import com.hrms.domain.common.pojo.Result;
import com.hrms.domain.user.dto.WorkerDTO;
import com.hrms.user.api.WorkerApi;
import com.hrms.user.service.WorkerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class WorkerApiController implements WorkerApi {
    @Autowired
    private WorkerService workerService;

    @Override
    public Result<String> addWorker(WorkerDTO workerDTO) {

        return null;
    }
}
