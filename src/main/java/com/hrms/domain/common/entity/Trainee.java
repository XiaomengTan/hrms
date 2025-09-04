package com.hrms.domain.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Trainee
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("trainee")
public class Trainee {
    private Integer id;
    private Integer lesId;
    private Integer workerId;
}