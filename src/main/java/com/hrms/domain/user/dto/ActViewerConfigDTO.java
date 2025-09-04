package com.hrms.domain.user.dto;

import com.hrms.domain.common.entity.ActConfigFriend;
import com.hrms.domain.common.entity.ActViewerConfig;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActViewerConfigDTO {
    private ActViewerConfig actViewerConfig;
    private List<ActConfigFriend> actConfigFriends;
}
