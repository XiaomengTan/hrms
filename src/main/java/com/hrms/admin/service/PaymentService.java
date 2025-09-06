package com.hrms.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hrms.domain.admin.dto.PaymentDTO;
import com.hrms.domain.common.dto.PageFullDataDTO;
import com.hrms.domain.common.entity.Payment;
import com.hrms.domain.common.pojo.PageBean;

public interface PaymentService extends IService<Payment> {
    /**
     * 添加今日账单
     */
    void addPaymentForm();

    /**
     * 分页查询账单
     * @param pageFullDataDTO
     * @return
     */
    PageBean getPaymentByPage(PageFullDataDTO pageFullDataDTO);

    /**
     * 修改账单
     * @param paymentDTO
     */
    void revisePayment(PaymentDTO paymentDTO);
}
