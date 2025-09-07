package com.hrms.domain.user.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TraineeVO {
    private Integer id;
    private String name;
    private String dept;
    private String job;
    private String lesName;
}
