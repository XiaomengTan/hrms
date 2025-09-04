package com.hrms.domain.user.dto;

import com.hrms.domain.common.entity.Channel;
import com.hrms.domain.common.entity.ChannelFile;
import com.hrms.domain.common.entity.ChannelMbrSettings;
import com.hrms.domain.common.entity.UserChannelSettings;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChannelDTO {
    private Channel channel;
    private UserChannelSettings userChannelSettings;
    private ChannelMbrSettings channelMbrSettings;
    private List<ChannelFile> channelFile;
}
