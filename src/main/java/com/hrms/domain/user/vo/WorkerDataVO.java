package com.hrms.domain.user.vo;

import com.hrms.domain.user.pojo.DepartmentWorkers;
import com.hrms.domain.user.pojo.WorkerAge;
import com.hrms.domain.user.pojo.WorkerGender;
import com.hrms.domain.user.pojo.WorkerType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkerDataVO {
    private WorkerType workerType;
    private WorkerGender workerGender;
    private WorkerAge workerAge;
    private List<DepartmentWorkers> departmentWorkers;
}
