package com.hrms.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hrms.admin.mapper.UserMapper;
import com.hrms.admin.service.UserService;
import com.hrms.annotation.Log;
import com.hrms.domain.admin.dto.UserDTO;
import com.hrms.domain.common.dto.PageDataDTO;
import com.hrms.domain.common.entity.User;
import com.hrms.domain.common.entity.Worker;
import com.hrms.domain.common.pojo.PageBean;
import com.hrms.user.mapper.WorkerMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private WorkerMapper workerMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 添加用户
     *
     * @param userDTO
     */
    @Log
    @Override
    public void addUser(UserDTO userDTO) {
        //检查name是否存在
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>()
                .eq(User::getName, userDTO.getName()));
        if (user != null) {
            throw new RuntimeException("用户名已被使用");
        }

        //检查worker是否存在
        Worker worker = workerMapper.selectOne(new LambdaQueryWrapper<Worker>()
                .eq(Worker::getId, userDTO.getWorker()));
        if (worker == null) {
            throw new RuntimeException("该员工不存在");
        }

        //检查目标worker是否已有管理员账户
        User userInDb = userMapper.selectOne(new LambdaQueryWrapper<User>()
                .eq(User::getWorker, userDTO.getWorker()));
        if (userInDb != null) {
            throw new  RuntimeException("该员工已有管理员账户");
        }

        //属性拷贝
        User newUser = new User();
        BeanUtils.copyProperties(userDTO, newUser);

        //密码加密操作
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));

        //添加数据
        userMapper.insert(newUser);

    }

    /**
     * 获取用户列表
     *
     * @param pageDataDTO
     * @return
     */
    @Log
    @Override
    public PageBean getUserList(PageDataDTO pageDataDTO) {
        Page<User> userPage = new Page<>(pageDataDTO.getPageNum(), pageDataDTO.getPageSize());

        //需排除password列
        IPage<User> userIPage = userMapper.selectPage(userPage, new LambdaQueryWrapper<User>()
                .select(User::getId, User::getName, User::getWorker));

        PageBean pageBean = new PageBean(userIPage.getTotal(), userIPage.getRecords());

        return pageBean;
    }
}
