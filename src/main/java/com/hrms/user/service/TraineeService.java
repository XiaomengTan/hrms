package com.hrms.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hrms.domain.common.dto.PageFullDataDTO;
import com.hrms.domain.common.entity.Trainee;
import com.hrms.domain.common.pojo.PageBean;
import com.hrms.domain.user.dto.TraineeDTO;
import org.springframework.stereotype.Service;


public interface TraineeService extends IService<Trainee> {
    /**
     * 向学员表中批量添加学员
     * @param traineeDTO
     */
    void addTrainees(TraineeDTO traineeDTO);

    /**
     * 批量查询学员
     * @param pageFullDataDTO
     * @return
     */
    PageBean getTraineesByPage(PageFullDataDTO pageFullDataDTO);

}
