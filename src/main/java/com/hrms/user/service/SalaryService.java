package com.hrms.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hrms.domain.common.dto.PageDataDTO;
import com.hrms.domain.common.dto.PageFullDataDTO;
import com.hrms.domain.common.entity.Salary;
import com.hrms.domain.common.pojo.PageBean;
import com.hrms.domain.user.dto.SalaryDTO;
import com.hrms.domain.user.dto.SalaryReviseDTO;
import com.hrms.domain.user.vo.SalaryAddVO;
import com.hrms.user.mapper.SalaryMapper;

public interface SalaryService extends IService<Salary> {
    /**
     * 添加薪资条
     * @param salaryDTO
     * @return
     */
    Salary addSalary(SalaryDTO salaryDTO);

    /**
     * 删除薪资条
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 修改薪资条
     * @param salaryReviseDTO
     */
    void reviseSalary(SalaryReviseDTO salaryReviseDTO);

    /**
     * 分页查询薪资
     * @param pageDataDTO
     * @return
     */
    PageBean getSalaryByPage(PageDataDTO pageDataDTO);


    /**
     * 模糊搜索薪资条
     * @param pageFullDataDTO
     * @return
     */
    PageBean getSalaryById(PageFullDataDTO pageFullDataDTO);

    /**
     * 查询被修改员工的薪资信息
     * @param id
     * @return
     */
    SalaryAddVO getAddVO(Integer id);
}
