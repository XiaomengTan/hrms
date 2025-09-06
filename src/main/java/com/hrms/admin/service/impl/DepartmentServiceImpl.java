package com.hrms.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hrms.admin.mapper.DepartmentMapper;
import com.hrms.admin.service.DepartmentService;
import com.hrms.annotation.Log;
import com.hrms.domain.admin.dto.DepartmentDTO;
import com.hrms.domain.admin.dto.DepartmentReviseDTO;
import com.hrms.domain.common.entity.Department;
import com.hrms.domain.common.entity.Worker;
import com.hrms.domain.common.enums.Const;
import com.hrms.user.mapper.WorkerMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private WorkerMapper workerMapper;

    /**
     * 新增部门
     *
     * @param departmentDTO
     */
    @Log
    @Override
    public void addDepartment(DepartmentDTO departmentDTO) {
        //首先检查部门名称是否重复
        Department departmentInDb = departmentMapper.selectOne(new LambdaQueryWrapper<Department>()
                .eq(Department::getName, departmentDTO.getName()));
        if (departmentInDb != null) {
            throw new RuntimeException("该部门已存在");
        }

        //然后检查部门是否有主管员工
        if (departmentDTO.getSupervisor() != Const.NO_SUPERVISOR) {
            Worker supervisor = workerMapper.selectById(departmentDTO.getSupervisor());
            //如果有，则检查主管是否存在
            if (supervisor == null) {
                throw new RuntimeException("该部门主管不存在");
            }
        }

        //检查上级部门的值
        if (departmentDTO.getUperId() == Const.ROOT_DEPARTMENT) {
            //如果为0，说明用户正在添加根节点
            Department rootDepartment = departmentMapper.selectOne(new LambdaQueryWrapper<Department>()
                    .eq(Department::getUperId, Const.ROOT_DEPARTMENT));
            // 检查根节点是否在数据库中存在
            if (rootDepartment != null) {
                //存在，添加失败，告诉用户根节点已存在
                throw new RuntimeException("根部门已经存在");
            }
        } else {
            //如果为其他值，说明用户正在添加叶子节点
            Department branchDepartment = departmentMapper.selectOne(new LambdaQueryWrapper<Department>()
                    .eq(Department::getId, departmentDTO.getUperId()));
            //检查上级节点是否存在
            if (branchDepartment == null) {
                //不存在，添加失败，告诉用户上级节点不存在
                throw new RuntimeException("上级部门不存在");
            }
        }
        //属性拷贝
        Department department = new Department();
        BeanUtils.copyProperties(departmentDTO, department);
        //添加部门
        departmentMapper.insert(department);
    }

    /**
     * 修改部门
     *
     * @param departmentReviseDTO
     */
    @Log
    @Override
    public void reviseDepartment(DepartmentReviseDTO departmentReviseDTO) {

        //检查是否修改部门名称并检查新名称是否重复
        String name = departmentReviseDTO.getName();
        if (name != null) {
            Department departmentInDb = departmentMapper.selectOne(new LambdaQueryWrapper<Department>()
                    .eq(Department::getName, name));
            if (departmentInDb != null) {
                throw new RuntimeException("部门已存在");
            }
        }

        //检查是否修改部门主管，改为无主管则直接设置为0，如果有主管就需要检查主管是否存在
        Integer supervisor = departmentReviseDTO.getSupervisor();
        if (supervisor != null && supervisor != Const.NO_SUPERVISOR) {
            Worker worker = workerMapper.selectById(supervisor);
            if (worker == null) {
                throw new RuntimeException("主管不存在");
            }
        }

        Integer uperId = departmentReviseDTO.getUperId();
        if (uperId != null) {
            //检查是否修改上级部门，不可能为0，如果为0，则代表库中没有部门，不可能发送修改请求
            //因为用户首先要在回显的数据修改界面修改内容，数据库得有数据才能发到修改接口，所以如果发送了0，代表用户输入错误
            if (uperId == Const.ROOT_DEPARTMENT) {
                throw new RuntimeException("输入错误，不可被修改为根部门");
            } else {
                //检查上级部门是否存在
                Department uperDepartment = departmentMapper.selectById(uperId);
                if (uperDepartment == null) {
                    throw new RuntimeException("上级部门不存在");
                }
            }
        }
        //创建修改后的部门对象
        Department department = new Department();
        BeanUtils.copyProperties(departmentReviseDTO, department);
        //执行修改操作
        departmentMapper.updateById(department);
    }

    /**
     * 删除部门
     *
     * @param id
     */
    @Log
    @Override
    @Transactional
    public void deleteDepartment(Integer id) {
        //所有该部门下的员工的department字段要设置为0
        workerMapper.update(new LambdaUpdateWrapper<Worker>()
                .eq(Worker::getDepartment, id)
                .set(Worker::getDepartment, Const.NO_ASSIGNED_DEPARTMENT)
        );
        //递归删除部门及其子部门
        deleteAllSubDepartments(id);
    }

    //递归删除子部门
    private void deleteAllSubDepartments(Integer departmentId) {
        List<Department> departments = departmentMapper.selectList(new LambdaQueryWrapper<Department>()
                .eq(Department::getUperId, departmentId));
        if (!departments.isEmpty()) {
            for (Department department : departments) {
                deleteAllSubDepartments(department.getId());
            }
        }
        departmentMapper.deleteById(departmentId);
    }
}
