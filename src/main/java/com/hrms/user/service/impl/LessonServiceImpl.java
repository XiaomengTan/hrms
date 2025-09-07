package com.hrms.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hrms.domain.common.dto.PageDataDTO;
import com.hrms.domain.common.dto.PageFullDataDTO;
import com.hrms.domain.common.entity.Lesson;
import com.hrms.domain.common.entity.Trainee;
import com.hrms.domain.common.entity.Worker;
import com.hrms.domain.common.pojo.PageBean;
import com.hrms.domain.user.dto.LessonDTO;
import com.hrms.domain.user.dto.LessonReviseDTO;
import com.hrms.user.mapper.LessonMapper;

import com.hrms.user.mapper.TraineeMapper;
import com.hrms.user.service.LessonService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class LessonServiceImpl extends ServiceImpl<LessonMapper, Lesson> implements LessonService {

    @Autowired
    private LessonMapper lessonMapper;

    @Autowired
    private TraineeMapper traineeMapper;

    /**
     * 添加课程
     *
     * @param lessonDTO
     */
    @Override
    public void addLesson(LessonDTO lessonDTO) {
        //检查该课程是否在数据库中存在
        Lesson lessonInDb = lessonMapper.selectOne(new LambdaQueryWrapper<Lesson>()
                .eq(Lesson::getName, lessonDTO.getName()));
        if (lessonInDb != null) {
            //课程已存在
            throw new RuntimeException("课程已存在");
        }

        //如果不存在，则属性拷贝
        Lesson lesson = new Lesson();
        BeanUtils.copyProperties(lessonDTO, lesson);

        //添加课程
        lessonMapper.insert(lesson);
    }

    /**
     * 根据id删除课程
     *
     * @param id
     */
    @Override
    @Transactional
    public void deleteById(Integer id) {
        //删除课程前，首先需要删除课程-员工关系表
        traineeMapper.delete(new LambdaQueryWrapper<Trainee>()
                .eq(Trainee::getLesId, id));
        //删除课程
        lessonMapper.deleteById(id);
    }

    @Override
    public Lesson reviseLesson(LessonReviseDTO lessonReviseDTO) {
        //提取课程名称
        String name = lessonReviseDTO.getName();
        //检查是否修改名称
        if (name != null) {
            //检查名称是否存在
            Lesson lesson = lessonMapper.selectOne(new LambdaQueryWrapper<Lesson>()
                    .eq(Lesson::getName, name));
            if (lesson != null) {
                throw new RuntimeException("名称已存在");
            }
        }
        //属性拷贝
        Lesson lesson = new Lesson();
        BeanUtils.copyProperties(lessonReviseDTO, lesson);
        //更新数据
        lessonMapper.updateById(lesson);
        return lessonMapper.selectById(lessonReviseDTO.getId());
    }

    /**
     * 分页查询课程
     *
     * @param pageDataDTO
     * @return
     */
    @Override
    public PageBean getLessonByPage(PageDataDTO pageDataDTO) {
        //创建分页对象
        Page<Lesson> page = new Page<>(pageDataDTO.getPageNum(), pageDataDTO.getPageSize());
        //封装查询结果
        IPage<Lesson> lessonIPage = lessonMapper.selectPage(page, null);
        //封装查询结果
        PageBean<Lesson> lessonPageBean = new PageBean<>();
        lessonPageBean.setTotal(lessonIPage.getTotal());
        lessonPageBean.setItems(lessonIPage.getRecords());
        return lessonPageBean;
    }

    /**
     * 根据id或name分页模糊查询课程
     *
     * @param pageFullDataDTO
     * @return
     */
    @Override
    public PageBean getLessonById(PageFullDataDTO pageFullDataDTO) {
        //创建分页对象
        Page<Lesson> page = new Page<>(pageFullDataDTO.getPageNum(), pageFullDataDTO.getPageSize());
        //封装查询结果
        IPage<Lesson> lessonIPage = lessonMapper.getLessonById(page, pageFullDataDTO.getId(), pageFullDataDTO.getName());
        //封装查询结果
        PageBean<Lesson> lessonPageBean = new PageBean<>();
        lessonPageBean.setTotal(lessonIPage.getTotal());
        lessonPageBean.setItems(lessonIPage.getRecords());
        return lessonPageBean;
    }

    /**
     * 查询所有课程
     */
    @Override
    public List<Lesson> getAllLesson() {
        List<Lesson> lessons = lessonMapper.selectList(null);
        return lessons;
    }
}
