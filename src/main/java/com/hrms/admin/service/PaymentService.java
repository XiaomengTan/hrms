package com.hrms.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hrms.domain.common.entity.Payment;

public interface PaymentService extends IService<Payment> {
    /**
     * 添加今日账单
     */
    void addPaymentForm();

}
