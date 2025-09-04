package com.hrms.domain.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("act_viewer_config")
public class ActViewerConfig {
    private Long id;
    private Long userId;
    private String configName;
    private Long frontId;
    private Long backId;
}
