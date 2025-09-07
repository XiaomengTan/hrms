package com.hrms.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hrms.domain.common.dto.PageDataDTO;
import com.hrms.domain.common.dto.PageFullDataDTO;
import com.hrms.domain.common.entity.Lesson;
import com.hrms.domain.common.pojo.PageBean;
import com.hrms.domain.user.dto.LessonDTO;
import com.hrms.domain.user.dto.LessonReviseDTO;

import java.util.List;

public interface LessonService extends IService<Lesson> {
    /**
     * 添加课程
     * @param lessonDTO
     */
    void addLesson(LessonDTO lessonDTO);

    /**
     * 根据id删除课程
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 修改课程
     * @param lessonReviseDTO
     * @return
     */
    Lesson reviseLesson(LessonReviseDTO lessonReviseDTO);

    /**
     * 分页查询课程
     * @param pageDataDTO
     * @return
     */
    PageBean getLessonByPage(PageDataDTO pageDataDTO);

    /**
     * 根据id或name分页模糊查询课程
     * @param pageFullDataDTO
     * @return
     */
    PageBean getLessonById(PageFullDataDTO pageFullDataDTO);

    /**
     * 查询所有课程
     */
    List<Lesson> getAllLesson();

}
