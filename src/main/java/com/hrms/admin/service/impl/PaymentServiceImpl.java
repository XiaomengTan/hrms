package com.hrms.admin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hrms.admin.mapper.PaymentMapper;
import com.hrms.admin.service.PaymentService;
import com.hrms.annotation.Log;
import com.hrms.domain.admin.dto.PaymentDTO;
import com.hrms.domain.common.dto.PageFullDataDTO;
import com.hrms.domain.common.entity.Payment;
import com.hrms.domain.common.pojo.PageBean;
import org.springframework.beans.BeanUtils;
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

    /**
     * 分页查询账单
     *
     * @param pageFullDataDTO
     * @return
     */
    @Log
    @Override
    public PageBean getPaymentByPage(PageFullDataDTO pageFullDataDTO) {
        Page<Payment> paymentPage = new Page<>(pageFullDataDTO.getPageNum(), pageFullDataDTO.getPageSize());

        IPage<Payment> paymentIPage = paymentMapper.selectPaymentByPage(paymentPage, pageFullDataDTO.getName());

        PageBean<Payment> paymentPageBean = new PageBean<>();
        paymentPageBean.setTotal(paymentIPage.getTotal());
        paymentPageBean.setItems(paymentIPage.getRecords());

        return paymentPageBean;
    }

    /**
     * 修改账单
     *
     * @param paymentDTO
     */
    @Log
    @Override
    public void revisePayment(PaymentDTO paymentDTO) {
        //检查目标账单是否在数据库中存在
        Payment paymentInDb = paymentMapper.selectById(paymentDTO.getId());
        if (paymentInDb == null) {
            throw new RuntimeException("该账单不存在");
        }

        //数据拷贝
        Payment payment = new Payment();
        BeanUtils.copyProperties(paymentDTO, payment);

        //修改成功
        paymentMapper.updateById(payment);
    }
}
