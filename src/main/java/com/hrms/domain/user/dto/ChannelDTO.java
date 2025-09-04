package com.hrms.domain.user.dto;

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
