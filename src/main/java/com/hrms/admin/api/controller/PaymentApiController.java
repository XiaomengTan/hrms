package com.hrms.admin.api.controller;

import com.hrms.admin.api.PaymentApi;
import com.hrms.admin.service.PaymentService;
import com.hrms.annotation.Log;
import com.hrms.domain.admin.dto.PaymentDTO;
import com.hrms.domain.common.dto.PageFullDataDTO;
import com.hrms.domain.common.pojo.PageBean;
import com.hrms.domain.common.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class PaymentApiController implements PaymentApi {

    @Autowired
    private PaymentService paymentService;

    //使用定时任务，每天凌晨0点创建一条当天的账单数据
//    @Async
//    @Scheduled(cron = "0 0 12 * * ?")
//    @Scheduled(cron = "*/5 * * * * ?")
    @Scheduled(cron = "0 0 0 * * ?")
    @Override
    public void addPaymentForm() {
//        log.info("每5秒执行一次...");
        log.info("正在添加今日账单...");
        paymentService.addPaymentForm();
        log.info("添加成功");
    }

    @Override
    public void addPaymentFormForTest() {
        log.info("正在添加今日账单...");
        paymentService.addPaymentForm();
        log.info("添加成功");
    }

    @Override
    public Result<PageBean> getPaymentByPage(PageFullDataDTO pageFullDataDTO) {
        log.info("分页查询或模糊搜索账单：{}", pageFullDataDTO);
        PageBean pageBean = paymentService.getPaymentByPage(pageFullDataDTO);
        log.info("分页查询成功");
        return Result.success(pageBean);
    }

    @Override
    public Result revisePayment(PaymentDTO paymentDTO) {
        log.info("修改某个账单：{}", paymentDTO);
        paymentService.revisePayment(paymentDTO);
        log.info("账单修改成功");
        return Result.success("账单修改成功");
    }
}
