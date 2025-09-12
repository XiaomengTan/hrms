package com.hrms.domain.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user")
public class User {
    private Integer id;
    private String name;
    private String password;
    private Integer worker;
}
