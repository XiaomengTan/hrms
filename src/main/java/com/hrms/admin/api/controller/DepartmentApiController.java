package com.hrms.admin.api.controller;

import com.hrms.admin.api.DepartmentApi;
import com.hrms.admin.service.DepartmentService;
import com.hrms.domain.admin.dto.DepartmentDTO;
import com.hrms.domain.admin.dto.DepartmentReviseDTO;
import com.hrms.domain.common.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class DepartmentApiController implements DepartmentApi {
    @Autowired
    private DepartmentService departmentService;


    @Override
    public Result addDepartment(DepartmentDTO departmentDTO) {
        log.info("新增部门: {}", departmentDTO);
        departmentService.addDepartment(departmentDTO);
        log.info("新增部门成功");
        return Result.success("新增部门成功");
    }

    @Override
    public Result reviseDepartment(DepartmentReviseDTO departmentReviseDTO) {
        log.info("修改部门信息: {}", departmentReviseDTO);
        departmentService.reviseDepartment(departmentReviseDTO);
        log.info("修改部门信息成功");
        return Result.success("修改部门成功");
    }

    @Override
    public Result deleteDepartment(Integer id) {
        log.info("删除部门: {}", id);
        departmentService.deleteDepartment(id);
        log.info("修改部门成功");
        return Result.success("修改部门成功");
    }
}
