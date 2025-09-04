package com.hrms.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface WorkerMapper extends BaseMapper<ActAgreeLog> {
    @Select("select * from act_agree_log where activity_id = #{activityId} and friend_id = #{userId}")
    ActAgreeLog selectByActivityIdAndUserId(Long activityId, Long userId);
}