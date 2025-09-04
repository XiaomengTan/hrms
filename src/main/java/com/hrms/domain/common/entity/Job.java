package com.hrms.domain.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("job")
public class Job {
    private Integer id;
    private String name;
    private String demand;
    private Integer type;
    private Integer salary;
    private Integer overtimePay;
    private String introduction;
    private Integer totalWorker;
}
