package com.hrms.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hrms.admin.mapper.UserMapper;
import com.hrms.admin.service.UserService;
import com.hrms.domain.admin.dto.UserDTO;
import com.hrms.domain.common.entity.User;
import com.hrms.domain.common.entity.Worker;
import com.hrms.user.mapper.WorkerMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private WorkerMapper workerMapper;

    /**
     * 添加用户
     *
     * @param userDTO
     */
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
            throw new RuntimeException("员工不存在");
        }

        //属性拷贝
        User newUser = new User();
        BeanUtils.copyProperties(userDTO, newUser);

        //添加数据
        userMapper.insert(newUser);

    }
}
