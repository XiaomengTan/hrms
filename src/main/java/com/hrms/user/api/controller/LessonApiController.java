package com.hrms.user.api.controller;

import com.hrms.domain.common.dto.PageDataDTO;
import com.hrms.domain.common.dto.PageFullDataDTO;
import com.hrms.domain.common.entity.Lesson;
import com.hrms.domain.common.pojo.PageBean;
import com.hrms.domain.common.pojo.Result;
import com.hrms.domain.user.dto.LessonDTO;
import com.hrms.domain.user.dto.LessonReviseDTO;
import com.hrms.user.api.LessonApi;
import com.hrms.user.service.LessonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LessonApiController implements LessonApi {
    @Autowired
    private LessonService lessonService;

    @Override
    public Result add(LessonDTO lessonDTO) {
        log.info("添加培训课程：{}", lessonDTO);
        lessonService.addLesson(lessonDTO);
        log.info("添加成功");
        return Result.success("添加成功");
    }

    @Override
    public Result delete(Integer id) {
        log.info("删除培训课程: {}", id);
        lessonService.deleteById(id);
        log.info("删除成功");
        return Result.success("删除成功");
    }

    @Override
    public Result revise(LessonReviseDTO lessonReviseDTO) {
        log.info("修改培训课程: {}", lessonReviseDTO);
        Lesson lesson = lessonService.reviseLesson(lessonReviseDTO);
        log.info("修改成功");
        return Result.success(lesson);
    }

    @Override
    public Result<PageBean> getByPage(PageDataDTO pageDataDTO) {
        log.info("分页查询: {}", pageDataDTO);
        PageBean pageBean = lessonService.getLessonByPage(pageDataDTO);
        log.info("分页查询成功");
        return Result.success(pageBean);
    }

    @Override
    public Result<PageBean> getById(PageFullDataDTO pageFullDataDTO) {

        log.info("分页查询: {}", pageFullDataDTO);
        PageBean pageBean = lessonService.getLessonById(pageFullDataDTO);
        log.info("分页查询成功");

        return Result.success(pageBean);
    }
}
