package com.hrms.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserGroupCategoryDTO {
    private UserGroupCategory oldUserGroupCategory;
    private UserGroupCategory newUserGroupCategory;
}
