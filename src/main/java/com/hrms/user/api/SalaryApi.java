package com.hrms.user.api;

import com.hrms.domain.common.dto.PageDataDTO;
import com.hrms.domain.common.dto.PageFullDataDTO;
import com.hrms.domain.common.entity.Salary;
import com.hrms.domain.common.pojo.PageBean;
import com.hrms.domain.common.pojo.Result;
import com.hrms.domain.user.dto.SalaryDTO;
import com.hrms.domain.user.dto.SalaryReviseDTO;
import com.hrms.domain.user.vo.SalaryAddVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "薪资管理", description = "薪资相关接口")
@Validated
@RequestMapping("/user/salary")
public interface SalaryApi {

    @Operation(summary = "添加薪资条")
    @PostMapping("/add")
    Result<Salary> addSalary(@RequestBody @Validated SalaryDTO salaryDTO);

    @Operation(summary = "修改薪资条的数据回显")
    @GetMapping("/getAddVO/{id}")
    Result<SalaryAddVO> getAddVO(@PathVariable @NotNull Integer id);

    @Operation(summary = "删除薪资条")
    @DeleteMapping("/delete/{id}")
    Result deleteSalary(@PathVariable @NotNull Integer id);

    @Operation(summary = "修改薪资条")
    @PutMapping("/revise")
    Result reviseSalary(@RequestBody @Validated SalaryReviseDTO salaryReviseDTO);

    @Operation(summary = "分页查询薪资条")
    @GetMapping("/getByPage")
    Result<PageBean> getByPage(@ModelAttribute @Validated @NotNull PageDataDTO pageDataDTO);

    @Operation(summary = "根据id和name模糊搜索薪资条")
    @GetMapping("/getById")
    Result<PageBean> getById(@ModelAttribute @Validated @NotNull PageFullDataDTO pageFullDataDTO);

}
