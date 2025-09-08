package com.hrms.domain.user.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkerAge {
    private Long young;
    private Long middle;
    private Long old;
}
