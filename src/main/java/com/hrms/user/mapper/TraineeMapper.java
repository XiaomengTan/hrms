package com.hrms.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hrms.domain.common.entity.Trainee;
import com.hrms.domain.common.entity.Worker;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TraineeMapper extends BaseMapper<Trainee> {
    IPage<Trainee> getTraineeById(Page<Trainee> page, Integer id, String name);
}
