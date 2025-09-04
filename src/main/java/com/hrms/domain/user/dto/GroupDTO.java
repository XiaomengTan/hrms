package com.hrms.domain.user.dto;

import com.hrms.domain.common.entity.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupDTO {
    private Group group;
    private List<GroupFile> groupFile;
    private GroupOperations groupOperations;
    private UserGroupSettings userGroupSettings;
    private GroupMbrSettings groupMbrSettings;
}
