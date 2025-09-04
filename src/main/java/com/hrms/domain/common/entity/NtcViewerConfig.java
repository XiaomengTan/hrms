package com.hrms.domain.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("ntc_viewer_config")
public class NtcViewerConfig {
    private Long id;
    private Byte owner;
    private Long ownerId;
    private String configName;
    private Long frontId;
    private Long backId;
}
