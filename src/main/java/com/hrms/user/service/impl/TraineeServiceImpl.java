package com.hrms.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hrms.admin.mapper.DepartmentMapper;
import com.hrms.domain.common.dto.PageDataDTO;
import com.hrms.domain.common.dto.PageFullDataDTO;
import com.hrms.domain.common.entity.*;
import com.hrms.domain.common.pojo.PageBean;
import com.hrms.domain.user.dto.TraineeDTO;
import com.hrms.domain.user.vo.TraineeVO;
import com.hrms.user.mapper.JobMapper;
import com.hrms.user.mapper.LessonMapper;
import com.hrms.user.mapper.TraineeMapper;
import com.hrms.user.mapper.WorkerMapper;
import com.hrms.user.service.TraineeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TraineeServiceImpl extends ServiceImpl<TraineeMapper, Trainee> implements TraineeService {

    @Autowired
    private TraineeMapper traineeMapper;

    @Autowired
    private LessonMapper lessonMapper;

    @Autowired
    private WorkerMapper workerMapper;
    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private JobMapper jobMapper;


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
        //删除旧的学员信息内容
        traineeMapper.delete(new LambdaQueryWrapper<Trainee>()
                .eq(Trainee::getLesId, lesId));
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
        IPage<Trainee> traineeIPage = traineeMapper.getTraineeById(page, pageFullDataDTO.getId(), pageFullDataDTO.getName());
        //封装查询结果
        PageBean<TraineeVO> traineePageBean = new PageBean<>();
        traineePageBean.setTotal(traineeIPage.getTotal());

        //封装视图对象
        List<TraineeVO> traineeVOS = new ArrayList<>();
        //遍历查询结果
        List<Trainee> records = traineeIPage.getRecords();
        for(Trainee trainee : records) {
            TraineeVO traineeVO = new TraineeVO();
            traineeVO.setId(trainee.getId());
            Integer workerId = trainee.getWorkerId();
            Integer lesId = trainee.getLesId();
            Worker worker = workerMapper.selectById(workerId);
            traineeVO.setName(worker.getName());
            Job job = jobMapper.selectById(worker.getJob());
            Department department = departmentMapper.selectById(worker.getDepartment());
            traineeVO.setJob(job.getName());
            traineeVO.setDept(department.getName());
            Lesson lesson = lessonMapper.selectById(lesId);
            traineeVO.setLesName(lesson.getName());
            traineeVOS.add(traineeVO);
        }
        traineePageBean.setItems(traineeVOS);
        return traineePageBean;
    }

    /**
     * 分页查询学员的映射关系表
     *
     * @param pageDataDTO
     * @return
     */
    @Override
    public PageBean getAllByPage(PageDataDTO pageDataDTO) {
        //创建分页对象
        Page<Trainee> page = new Page<>(pageDataDTO.getPageNum(), pageDataDTO.getPageSize());
        //封装查询结果
        IPage<Trainee> traineeIPage = traineeMapper.selectPage(page, null);
        //封装查询结果
        PageBean<Trainee> traineePageBean = new PageBean<>();
        traineePageBean.setTotal(traineeIPage.getTotal());
        traineePageBean.setItems(traineeIPage.getRecords());
        return traineePageBean;
    }

    /**
     * 查询所有不在某课程列表的学员名单
     *
     * @param lessonId
     * @return
     */
    @Override
    public List<Worker> getNotInLessonList(Integer lessonId) {
        //查询课程的学员名单
        List<Integer> trainees = traineeMapper.selectObjs(
                        new LambdaQueryWrapper<Trainee>()
                                .select(Trainee::getWorkerId) // 明确指定查询字段
                                .eq(Trainee::getLesId, lessonId)
                ).stream()
                .map(obj -> (Integer) obj) // 将Object转换为Integer
                .collect(Collectors.toList());
        List<Worker> workers = workerMapper.selectList(new LambdaQueryWrapper<Worker>()
                .notIn(Worker::getId, trainees));
        return workers;
    }

    /**
     * 查询所有在某课程列表的学员名单
     *
     * @param lessonId
     * @return
     */
    @Override
    public List<Worker> getInLessonList(Integer lessonId) {
        //查询课程的学员名单
        List<Integer> trainees = traineeMapper.selectObjs(
                        new LambdaQueryWrapper<Trainee>()
                                .select(Trainee::getWorkerId) // 明确指定查询字段
                                .eq(Trainee::getLesId, lessonId)
                ).stream()
                .map(obj -> (Integer) obj) // 将Object转换为Integer
                .collect(Collectors.toList());
        List<Worker> workers = workerMapper.selectList(new LambdaQueryWrapper<Worker>()
                .in(Worker::getId, trainees));
        return workers;
    }
}
