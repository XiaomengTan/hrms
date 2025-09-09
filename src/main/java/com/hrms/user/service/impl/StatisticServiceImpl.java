package com.hrms.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hrms.admin.mapper.DepartmentMapper;
import com.hrms.admin.mapper.PaymentMapper;
import com.hrms.domain.common.entity.*;
import com.hrms.domain.common.enums.Const;
import com.hrms.domain.user.pojo.*;
import com.hrms.domain.user.vo.OverviewPageVO;
import com.hrms.domain.user.vo.SalaryVO;
import com.hrms.domain.user.vo.WorkerDataVO;
import com.hrms.user.mapper.JobMapper;
import com.hrms.user.mapper.SalaryMapper;
import com.hrms.user.mapper.WorkerMapper;
import com.hrms.user.service.StatisticService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

@Service
public class StatisticServiceImpl implements StatisticService {
    private final WorkerMapper workerMapper;
    private final JobMapper jobMapper;
    private final DepartmentMapper departmentMapper;
    private final PaymentMapper paymentMapper;
    private final SalaryMapper salaryMapper;

    public StatisticServiceImpl(WorkerMapper workerMapper, JobMapper jobMapper, DepartmentMapper departmentMapper, PaymentMapper paymentMapper, SalaryMapper salaryMapper) {
        this.workerMapper = workerMapper;
        this.jobMapper = jobMapper;
        this.departmentMapper = departmentMapper;
        this.paymentMapper = paymentMapper;
        this.salaryMapper = salaryMapper;
    }

    /**
     * 获取数据总览页统计信息
     *
     * @return
     */
    @Override
    public OverviewPageVO getOverviewData() {
        //创建OverviewPageVO对象
        OverviewPageVO overviewPageVO = new OverviewPageVO();
        //创建子字段
        PanelData panelData = buildPanelData();
        List<List<Long>> workerGrowthTrend = buildWorkerGrowthTrend();
        SalaryDistribution salaryDistribution = buildSalaryDistribution();
        List<List<Double>> incomeExpenseTrend = buildIncomeExpenseTrend();

        //将他们都添加到OverviewPageVO对象中
        overviewPageVO.setPanelData(panelData);
        overviewPageVO.setWorkerGrowthTrend(workerGrowthTrend);
        overviewPageVO.setSalaryDistribution(salaryDistribution);
        overviewPageVO.setIncomeExpenseTrend(incomeExpenseTrend);

        return overviewPageVO;
    }

    /**
     * 获取员工统计页统计信息
     *
     * @return
     */
    @Override
    public WorkerDataVO getWorkerData() {
        //创建vo对象
        WorkerDataVO workerDataVO = new WorkerDataVO();
        //创建子字段
        WorkerType workerType = buildWorkerType();
        WorkerGender workerGender = buildWorkerGender();
        WorkerAge workerAge = buildWorkerAge();
        List<DepartmentWorkers> departmentWorkers = buildDepartmentWorkers();
        //封装到vo对象中
        workerDataVO.setWorkerType(workerType);
        workerDataVO.setWorkerGender(workerGender);
        workerDataVO.setWorkerAge(workerAge);
        workerDataVO.setDepartmentWorkers(departmentWorkers);

        return workerDataVO;
    }

    /**
     * 获取员工薪资条信息
     *
     * @return
     */
    @Override
    public List<SalaryVO> getWorkerSalaryData(Integer id) {
        //根据id查询薪资表，获取该员工所有的薪资数据
        List<Salary> salaries = salaryMapper.selectList(new LambdaQueryWrapper<Salary>()
                .eq(Salary::getWorkerId, id));
        //将薪资条的总薪资和发放时间拷贝到SalaryVO中
        List<SalaryVO> salaryVOList = new ArrayList<>();
        for (Salary salary : salaries) {
            SalaryVO salaryVO = new SalaryVO();
            BeanUtils.copyProperties(salary, salaryVO);
            salaryVOList.add(salaryVO);
        }

        return salaryVOList;
    }

    private List<DepartmentWorkers> buildDepartmentWorkers() {
        List<DepartmentWorkers> departmentWorkers = new ArrayList<>();
        //查询部门列表
        List<Department> departmentIdList = departmentMapper.selectList(null);

        //遍历，从worker表中查询相关
        for (Department department : departmentIdList) {
            //查询某部门的
            Long deptWorkerAmount = workerMapper.selectCount(new LambdaQueryWrapper<Worker>()
                    .eq(Worker::getDepartment, department.getId()));
            //封装到对象中
            DepartmentWorkers departmentWorkersTemp = new DepartmentWorkers();
            departmentWorkersTemp.setDepartmentName(department.getName());
            departmentWorkersTemp.setWorkerAmount(deptWorkerAmount);
            departmentWorkers.add(departmentWorkersTemp);
        }
        return departmentWorkers;
    }

    private WorkerAge buildWorkerAge() {
        //获取所有员工列表
        List<Worker> workers = workerMapper.selectList(null);

        //创建本日日期
        LocalDate localDate = LocalDate.now();
        //创建WorkerAge对象并全部初始化为0
        WorkerAge workerAge = new WorkerAge(0L, 0L, 0L);
        //遍历员工列表
        for (Worker worker : workers) {
            //计算员工年龄
            LocalDate birthday = worker.getBirthday();
            Period period = Period.between(birthday, localDate);
            Integer age = period.getYears();
            //判断此年龄在何范围中
            if (age < Const.MIDDLE_AGE) {
                workerAge.setYoung(workerAge.getYoung() + 1);
            } else if (age <= Const.OLD_AGE) {
                workerAge.setMiddle(workerAge.getMiddle() + 1);
            } else {
                workerAge.setOld(workerAge.getOld() + 1);
            }
        }
        return workerAge;
    }

    private WorkerGender buildWorkerGender() {
        WorkerGender workerGender = new WorkerGender();
        //查询正式工总数
        Long maleAmount = workerMapper.selectCount(new LambdaQueryWrapper<Worker>()
                .eq(Worker::getGender, Const.MALE));
        //查询临时工总数
        Long femaleAmount = workerMapper.selectCount(new LambdaQueryWrapper<Worker>()
                .eq(Worker::getGender, Const.FEMALE));
        //封装
        workerGender.setMale(maleAmount);
        workerGender.setFemale(femaleAmount);

        return workerGender;
    }

    private WorkerType buildWorkerType() {
        WorkerType workerType = new WorkerType();
        //查询正式工总数
        Long permanentWorkerAmount = workerMapper.selectCount(new LambdaQueryWrapper<Worker>()
                .eq(Worker::getLaborStatus, Const.PERMANENT_WORKER));
        //查询临时工总数
        Long temporaryWorkerAmount = workerMapper.selectCount(new LambdaQueryWrapper<Worker>()
                .eq(Worker::getLaborStatus, Const.TEMPORARY_WORKER));
        //查询实习生总数
        Long internWorkerAmount = workerMapper.selectCount(new LambdaQueryWrapper<Worker>()
                .eq(Worker::getLaborStatus, Const.INTERN_WORKER));
        //封装
        workerType.setPermanent(permanentWorkerAmount);
        workerType.setTemporary(temporaryWorkerAmount);
        workerType.setIntern(internWorkerAmount);

        return workerType;
    }

    private List<List<Double>> buildIncomeExpenseTrend() {
        List<List<Double>> incomeExpenseTrend = new ArrayList<>();
        //计算十二个月的支出数据
        for (int i = 11; i >= 0; i--) {
            List<Double> monthData = new ArrayList<>();
            LocalDate startDate = LocalDate.now().minusMonths(i).withDayOfMonth(1);
            LocalDate endDate = LocalDate.now().minusMonths(i).with(TemporalAdjusters.lastDayOfMonth());
            //查询当前月每天的收支数据
            List<Payment> payments = paymentMapper.selectList(new LambdaQueryWrapper<Payment>()
                    .between(Payment::getDate, startDate, endDate));
            // 对 profit 字段求和
            double totalProfit = payments.stream()
                    .mapToDouble(Payment::getProfit)
                    .sum();

            // 对 outlay 字段求和
            double totalOutlay = payments.stream()
                    .mapToDouble(Payment::getOutlay)
                    .sum();
            monthData.add(totalProfit);
            monthData.add(totalOutlay);
            incomeExpenseTrend.add(monthData);
        }
        return incomeExpenseTrend;
    }

    private SalaryDistribution buildSalaryDistribution() {
        //先获取五个不同薪资水平的岗位列表。
        //few薪资水平
        List<Integer> fewJobList = jobMapper.selectList(new LambdaQueryWrapper<Job>()
                        .between(Job::getSalary, Const.SALARY_FEW, Const.SALARY_LOW))
                .stream()
                .map(Job::getId)
                .toList();

        //low薪资水平
        List<Integer> lowJobList = jobMapper.selectList(new LambdaQueryWrapper<Job>()
                        .between(Job::getSalary, Const.SALARY_LOW, Const.SALARY_MiDDLE))
                .stream()
                .map(Job::getId)
                .toList();

        //middle薪资水平
        List<Integer> middleJobList = jobMapper.selectList(new LambdaQueryWrapper<Job>()
                        .between(Job::getSalary, Const.SALARY_MiDDLE, Const.SALARY_HIGH))
                .stream()
                .map(Job::getId)
                .toList();

        //high薪资水平
        List<Integer> highJobList = jobMapper.selectList(new LambdaQueryWrapper<Job>()
                        .between(Job::getSalary, Const.SALARY_HIGH, Const.SALARY_MORE))
                .stream()
                .map(Job::getId)
                .toList();

        //more薪资水平
        List<Integer> moreJobList = jobMapper.selectList(new LambdaQueryWrapper<Job>()
                        .gt(Job::getSalary, Const.SALARY_MORE))
                .stream()
                .map(Job::getId)
                .toList();

        //再分别使用in语句查询job在对应层级的列表的员工数量。
        Long fewWorkerAmount = workerMapper.selectCount(new LambdaQueryWrapper<Worker>()
                .in(Worker::getJob, fewJobList));
        Long lowWorkerAmount = workerMapper.selectCount(new LambdaQueryWrapper<Worker>()
                .in(Worker::getJob, lowJobList));
        Long middleWorkerAmount = workerMapper.selectCount(new LambdaQueryWrapper<Worker>()
                .in(Worker::getJob, middleJobList));
        Long highWorkerAmount = workerMapper.selectCount(new LambdaQueryWrapper<Worker>()
                .in(Worker::getJob, highJobList));
        Long moreWorkerAmount = workerMapper.selectCount(new LambdaQueryWrapper<Worker>()
                .in(Worker::getJob, moreJobList));
        //封装到对象中
        SalaryDistribution salaryDistribution = new SalaryDistribution();
        salaryDistribution.setFew(fewWorkerAmount);
        salaryDistribution.setLow(lowWorkerAmount);
        salaryDistribution.setMiddle(middleWorkerAmount);
        salaryDistribution.setHigh(highWorkerAmount);
        salaryDistribution.setMore(moreWorkerAmount);
        return salaryDistribution;
    }

    private List<List<Long>> buildWorkerGrowthTrend() {
        List<List<Long>> workerGrowthTrend = new ArrayList<>();
        //计算十二个月的数据
        for (int i = 11; i >= 0; i--) {
            List<Long> monthData = new ArrayList<>();
            LocalDate startDate = LocalDate.now().minusMonths(i).withDayOfMonth(1);
            LocalDate endDate = LocalDate.now().minusMonths(i).with(TemporalAdjusters.lastDayOfMonth());
            //查询当前月入职的员工总数
            Long join = workerMapper.selectCount(new LambdaQueryWrapper<Worker>()
                    .between(Worker::getOnboardingTime, startDate, endDate));
            //查询当前月离职的员工总数
            Long resigned = workerMapper.selectCount(new LambdaQueryWrapper<Worker>()
                    .between(Worker::getDepartureTime, startDate, endDate));
            monthData.add(join);
            monthData.add(resigned);
            workerGrowthTrend.add(monthData);
        }
        return workerGrowthTrend;
    }

    private PanelData buildPanelData() {

        PanelData panelData = new PanelData();
        //计算员工总数
        Long workerAmount = workerMapper.selectCount(null);
        //计算在职员工总数
        Long employedAmount = workerMapper.selectCount(new LambdaQueryWrapper<Worker>()
                .eq(Worker::getWorkStatus, Const.WORKING));
        //计算总岗位数
        Long jobAmount = jobMapper.selectCount(null);
        //计算总部门数
        Long departmentAmount = departmentMapper.selectCount(null);
        //计算本月月初前入职的员工数
        LocalDate firstDayOfMonth = LocalDate.now().withDayOfMonth(1);
        Long beforeWorkerAmount = workerMapper.selectCount(new LambdaQueryWrapper<Worker>()
                .lt(Worker::getOnboardingTime, firstDayOfMonth));
        //计算增幅
        Long workerChange = workerAmount - beforeWorkerAmount;

        //封装到对象
        panelData.setWorkerAmount(workerAmount);
        panelData.setEmployedAmount(employedAmount);
        panelData.setJobAmount(jobAmount);
        panelData.setDepartmentAmount(departmentAmount);
        panelData.setWorkerChange(workerChange);

        return panelData;
    }
}
