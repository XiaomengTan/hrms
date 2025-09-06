package com.hrms.admin.api;


import com.hrms.domain.admin.dto.DepartmentDTO;
import com.hrms.domain.admin.dto.DepartmentReviseDTO;
import com.hrms.domain.common.pojo.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "系统部门管理", description = "系统部门管理")
@RequestMapping("/admin/department")
@Validated
public interface DepartmentApi {

    @Operation(summary = "新增部门")
    @PostMapping("/add")
    Result addDepartment(@RequestBody @Validated DepartmentDTO departmentDTO);

    @Operation(summary = "修改部门信息")
    @PutMapping("/revise")
    Result reviseDepartment(@RequestBody @Validated DepartmentReviseDTO departmentReviseDTO);

    @Operation(summary = "删除部门")
    @DeleteMapping("/delete/{id}")
    Result deleteDepartment(@PathVariable Integer id);
}
