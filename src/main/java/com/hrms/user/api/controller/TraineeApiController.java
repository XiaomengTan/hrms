package com.hrms.user.api.controller;

import com.hrms.domain.common.dto.PageDataDTO;
import com.hrms.domain.common.dto.PageFullDataDTO;
import com.hrms.domain.common.entity.Worker;
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
        log.info("向{}课程更新学员信息：{}", traineeDTO.getLesId(), traineeDTO.getWorkerIds());
        traineeService.addTrainees(traineeDTO);
        log.info("更新成功");
        return Result.success("更新成功");
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
        log.info("查询成功");
        return Result.success(pageBean);
    }

    @Override
    public Result<List<Worker>> getNotInLessonList(Integer id) {
        log.info("查询不在{}课程的学员：", id);
        List<Worker> notInLessonList = traineeService.getNotInLessonList(id);
        log.info("查询成功");
        return Result.success(notInLessonList);
    }

    @Override
    public Result<List<Worker>> getInLessonList(Integer id) {
        log.info("查询在{}课程的学员：", id);
        List<Worker> inLessonList = traineeService.getInLessonList(id);
        log.info("查询成功");
        return Result.success(inLessonList);
    }

    @Override
    public Result<PageBean> getAllTrainees(PageDataDTO pageDataDTO) {
        log.info("查询映射关系表");
        PageBean pageBean = traineeService.getAllByPage(pageDataDTO);
        log.info("查询映射关系表成功");
        return Result.success(pageBean);
    }
}
