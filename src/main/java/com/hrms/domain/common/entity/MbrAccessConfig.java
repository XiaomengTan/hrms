package com.hrms.domain.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("mbr_access_config")
public class MbrAccessConfig {
    private Long id;
    private Long groupId;
    private String configName;
    private Long frontId;
    private Long backId;
}
