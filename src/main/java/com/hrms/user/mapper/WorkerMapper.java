package com.hrms.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hrms.domain.common.entity.Worker;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface WorkerMapper extends BaseMapper<Worker> {

    @Select("select * from worker where name = #{name}")
    Worker selectByName(String name);

    IPage<Worker> getWorkerById(Page<Worker> page, Integer id, String name);

    @Select("select * from worker where job = #{id}")
    IPage<Worker> getWorkerByJob(Page<Worker> page, Integer id);
}