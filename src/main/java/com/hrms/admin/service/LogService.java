package com.hrms.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hrms.domain.common.dto.PageFullDataDTO;
import com.hrms.domain.common.entity.Log;
import com.hrms.domain.common.pojo.PageBean;

public interface LogService extends IService<Log> {
    /**
     * 分页获取日志信息
     * @param pageFullDataDTO
     * @return
     */
    PageBean getOptionalByPage(PageFullDataDTO pageFullDataDTO);

    /**
     * 按日期查询
     * @param pageFullDataDTO
     * @return
     */
    PageBean getOptionalByDate(PageFullDataDTO pageFullDataDTO);
}
