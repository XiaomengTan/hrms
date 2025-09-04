package com.hrms.domain.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("mbr_config_channel")
public class MbrConfigChannel {
    private Long id;
    private Long configId;
    private Long channelId;
}
