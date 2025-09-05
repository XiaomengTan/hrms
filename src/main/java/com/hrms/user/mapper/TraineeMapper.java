package com.hrms.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hrms.domain.common.entity.Trainee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TraineeMapper extends BaseMapper<Trainee> {
    @Select("select * from trainee where les_id = #{id}")
    IPage<Trainee> getTraineeByPage(Page<Trainee> page, Integer id);
}
