package com.hrms.domain.user.dto;

import com.hrms.domain.common.entity.MbrAccessConfig;
import com.hrms.domain.common.entity.MbrConfigChannel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MbrAccessConfigDTO {
    private MbrAccessConfig mbrAccessConfig;
    private List<MbrConfigChannel> mbrConfigChannels;
}
