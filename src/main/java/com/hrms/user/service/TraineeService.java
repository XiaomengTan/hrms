package com.hrms.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hrms.domain.common.dto.PageDataDTO;
import com.hrms.domain.common.dto.PageFullDataDTO;
import com.hrms.domain.common.entity.Trainee;
import com.hrms.domain.common.entity.Worker;
import com.hrms.domain.common.pojo.PageBean;
import com.hrms.domain.user.dto.TraineeDTO;
import org.springframework.stereotype.Service;

import java.util.List;


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

    /**
     * 分页查询学员的映射关系表
     * @param pageDataDTO
     * @return
     */
    PageBean getAllByPage(PageDataDTO pageDataDTO);

    /**
     * 查询所有不在某课程列表的学员名单
     * @param id
     * @return
     */
    List<Worker> getNotInLessonList(Integer id);

    /**
     * 查询所有在某课程列表的学员名单
     * @param id
     * @return
     */
    List<Worker> getInLessonList(Integer id);
}
