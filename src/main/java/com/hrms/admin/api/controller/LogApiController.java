package com.hrms.admin.api.controller;

import com.hrms.admin.api.LogApi;
import com.hrms.admin.service.LogService;
import com.hrms.domain.common.dto.PageDataDTO;
import com.hrms.domain.common.dto.PageFullDataDTO;
import com.hrms.domain.common.pojo.PageBean;
import com.hrms.domain.common.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LogApiController implements LogApi {

    @Autowired
    private LogService logService;


    @Override
    public Result<PageBean> getOptionalByPage(PageFullDataDTO pageFullDataDTO) {
        log.info("分页获取日志信息：{}", pageFullDataDTO);
        PageBean pageBean = logService.getOptionalByPage(pageFullDataDTO);
        log.info("分页查询成功");
        return Result.success(pageBean);
    }

    @Override
    public Result<PageBean> getOperationByDate(PageFullDataDTO pageFullDataDTO) {
        log.info("按日期查询日志信息：{}", pageFullDataDTO);
        PageBean pageBean = logService.getOptionalByDate(pageFullDataDTO);
        log.info("查询成功");
        return Result.success(pageBean);
    }
}
