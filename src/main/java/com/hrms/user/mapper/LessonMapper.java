package com.hrms.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hrms.domain.common.entity.Lesson;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LessonMapper extends BaseMapper<Lesson> {

    IPage<Lesson> getLessonById(Page<Lesson> page, Integer id, String name);
}
