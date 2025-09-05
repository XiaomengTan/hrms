package com.hrms.user.api.controller;

import com.hrms.domain.common.dto.PageFullDataDTO;
import com.hrms.domain.common.entity.Trainee;
import com.hrms.domain.common.pojo.PageBean;
import com.hrms.domain.common.pojo.Result;
import com.hrms.domain.user.dto.TraineeDTO;
import com.hrms.user.api.TraineeApi;
import com.hrms.user.service.TraineeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class TraineeApiController implements TraineeApi {
    @Autowired
    private TraineeService traineeService;

    @Override
    public Result addTrainees(TraineeDTO traineeDTO) {
        log.info("向{}课程添加学员：{}", traineeDTO.getLesId(), traineeDTO.getWorkerIds());
        traineeService.addTrainees(traineeDTO);
        log.info("添加成功");
        return Result.success("添加成功");
    }

    @Override
    public Result deleteTrainees(List<Integer> ids) {
        log.info("批量删除学员：{}", ids);
        traineeService.removeByIds(ids);
        log.info("删除成功");
        return Result.success("删除成功");
    }

    @Override
    public Result<PageBean> getTraineesByPage(PageFullDataDTO pageFullDataDTO) {
        log.info("分页查询{}课程的学员：", pageFullDataDTO.getId());
        PageBean pageBean = traineeService.getTraineesByPage(pageFullDataDTO);
        log.info("删除成功");
        return Result.success(pageBean);
    }
}
