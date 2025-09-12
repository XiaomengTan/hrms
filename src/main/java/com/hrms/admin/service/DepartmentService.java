package com.hrms.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hrms.domain.admin.dto.DepartmentDTO;
import com.hrms.domain.admin.dto.DepartmentReviseDTO;
import com.hrms.domain.common.entity.Department;

import java.util.List;

public interface DepartmentService extends IService<Department> {

    /**
     * 新增部门
     * @param departmentDTO
     */
    void addDepartment(DepartmentDTO departmentDTO);

    /**
     * 修改部门
     * @param departmentReviseDTO
     */
    void reviseDepartment(DepartmentReviseDTO departmentReviseDTO);

    /**
     * 删除部门
     * @param id
     */
    void deleteDepartment(Integer id);

    /**
     *
     * 分页查询部门列表
     * @return
     */
    List<Department> getAllDept();
}
