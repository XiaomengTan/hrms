package com.hrms.user.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hrms.domain.common.dto.PageFullDataDTO;
import com.hrms.domain.common.entity.Lesson;
import com.hrms.domain.common.entity.Trainee;
import com.hrms.domain.common.entity.Worker;
import com.hrms.domain.common.pojo.PageBean;
import com.hrms.domain.user.dto.TraineeDTO;
import com.hrms.user.mapper.LessonMapper;
import com.hrms.user.mapper.TraineeMapper;
import com.hrms.user.mapper.WorkerMapper;
import com.hrms.user.service.TraineeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TraineeServiceImpl extends ServiceImpl<TraineeMapper, Trainee> implements TraineeService {

    @Autowired
    private TraineeMapper traineeMapper;

    @Autowired
    private LessonMapper lessonMapper;

    @Autowired
    private WorkerMapper workerMapper;


    /**
     * 向学员表中批量添加学员
     *
     * @param traineeDTO
     */
    @Override
    @Transactional
    public void addTrainees(TraineeDTO traineeDTO) {
        //提取值
        Integer lesId = traineeDTO.getLesId();
        List<Integer> workerIds = traineeDTO.getWorkerIds();
        //检查课程是否在数据库中存在
        Lesson lesson = lessonMapper.selectById(lesId);
        if (lesson == null) {
            throw new RuntimeException("课程不存在");
        }
        //将列表加入
        List<Trainee> trainees = new ArrayList<>();
        for(Integer workerId : workerIds) {
            //检查workerid是否存在
            Worker worker = workerMapper.selectById(workerId);
            if (worker == null) {
                throw new RuntimeException("员工不存在" + workerId);
            }
            //创建对象并插入数据库中
            Trainee trainee = new Trainee();
            trainee.setLesId(lesId);
            trainee.setWorkerId(workerId);
            trainees.add(trainee);
        }
        //批量插入数据库中
        traineeMapper.insert(trainees);
    }

    /**
     * 批量查询学员
     *
     * @param pageFullDataDTO
     * @return
     */
    @Override
    public PageBean getTraineesByPage(PageFullDataDTO pageFullDataDTO) {
        //创建分页对象
        Page<Trainee> page = new Page<>(pageFullDataDTO.getPageNum(), pageFullDataDTO.getPageSize());
        //封装查询结果
        IPage<Trainee> traineeIPage = traineeMapper.getTraineeByPage(page, pageFullDataDTO.getId());
        //封装查询结果
        PageBean<Worker> traineePageBean = new PageBean<>();
        traineePageBean.setTotal(traineeIPage.getTotal());
        //注意，这里查出来的还是worker的id，还需要遍历查询worker表才能得到学员的信息
        List<Trainee> records = traineeIPage.getRecords();
        List<Worker> workers = new ArrayList<>();
        for (Trainee trainee : records) {
            Worker worker = workerMapper.selectById(trainee.getWorkerId());
            if (worker != null) {
                workers.add(worker);
            }
        }
        traineePageBean.setItems(workers);
        return traineePageBean;
    }
}
