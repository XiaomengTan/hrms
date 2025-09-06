package com.hrms.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hrms.domain.common.entity.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;

@Mapper
public interface PaymentMapper extends BaseMapper<Payment> {


    IPage<Payment> selectPaymentByPage(Page<Payment> paymentPage, String keyWord);

    @Select("select * from payment where date = #{settlememtTime}")
    Payment selectByDate(LocalDate settlementTime);
}
