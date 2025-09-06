package com.hrms.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hrms.domain.common.entity.Payment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentMapper extends BaseMapper<Payment> {
}
