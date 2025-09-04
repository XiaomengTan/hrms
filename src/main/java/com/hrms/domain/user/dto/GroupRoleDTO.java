package com.hrms.domain.user.dto;

import com.hrms.domain.common.entity.GroupRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupRoleDTO {
    private GroupRole oldGroupRole;
    private GroupRole newGroupRole;
}
