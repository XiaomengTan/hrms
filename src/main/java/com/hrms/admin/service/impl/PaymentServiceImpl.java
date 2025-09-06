package com.hrms.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hrms.admin.mapper.PaymentMapper;
import com.hrms.admin.service.PaymentService;
import com.hrms.annotation.Log;
import com.hrms.domain.common.entity.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PaymentServiceImpl extends ServiceImpl<PaymentMapper, Payment> implements PaymentService {

    @Autowired
    private PaymentMapper paymentMapper;

    /**
     * 添加今日账单
     */
    @Log
    @Override
    public void addPaymentForm() {
        Payment payment = new Payment();
        payment.setDate(LocalDate.now());
        paymentMapper.insert(payment);
    }
}
