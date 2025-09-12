package com.hrms.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hrms.admin.mapper.LogMapper;
import com.hrms.admin.service.LogService;
import com.hrms.domain.common.dto.PageFullDataDTO;
import com.hrms.domain.common.entity.Log;
import com.hrms.domain.common.pojo.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements LogService {

    @Autowired
    private LogMapper logMapper;

    /**
     * 分页获取日志信息
     *
     * @param pageFullDataDTO
     * @return
     */
    @com.hrms.annotation.Log
    @Override
    public PageBean getOptionalByPage(PageFullDataDTO pageFullDataDTO) {

        Page<Log> logPage = new Page<>(pageFullDataDTO.getPageNum(), pageFullDataDTO.getPageSize());

        IPage<Log> logIPage = logMapper.getOptionalByPage(logPage, pageFullDataDTO.getName());

        PageBean<Log> logPageBean = new PageBean<>(logIPage.getTotal(), logIPage.getRecords());

        return logPageBean;
    }

    /**
     * 按日期查询
     *
     * @param pageFullDataDTO
     * @return
     */
    @com.hrms.annotation.Log
    @Override
    public PageBean getOptionalByDate(PageFullDataDTO pageFullDataDTO) {

        Page<Log> logPage = new Page<>(pageFullDataDTO.getPageNum(), pageFullDataDTO.getPageSize());

        IPage<Log> logIPage = logMapper.selectPage(logPage, new LambdaQueryWrapper<Log>()
                .apply("DATE(operate_time) = {0}", pageFullDataDTO.getName()));

        PageBean<Log> logPageBean = new PageBean<>(logIPage.getTotal(), logIPage.getRecords());

        return logPageBean;
    }
}
