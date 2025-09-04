package com.hrms.domain.common.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * Log
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("Log")
public class Log {
    private String className;
    private Double costTime;
    private Integer id;
    private String methodName;
    private String methodParams;
    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    private LocalDateTime operateTime;
    private Long operateUser;
    private String returnValue;
}