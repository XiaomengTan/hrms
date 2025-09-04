package com.hrms.domain.user.dto;

import com.hrms.domain.common.entity.UserChannelCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserChannelCategoryDTO {
    private UserChannelCategory oldUserChannelCategory;
    private UserChannelCategory newUserChannelCategory;
}
