package com.hrms.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hrms.domain.common.entity.Job;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface JobMapper extends BaseMapper<Job> {
    @Select("select * from job where name = #{name}")
    Job selectByName(String name);

    IPage<Job> getJobById(Page<Job> page, Integer id, String name);
}
