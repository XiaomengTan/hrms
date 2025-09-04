package com.hrms.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FriendCategoryDTO {
    private FriendCategory oldPlace;
    private FriendCategory newPlace;
}
