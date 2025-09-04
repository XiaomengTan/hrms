package com.hrms.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DialogDTO {
    private Long headDialogId;
    private Long tailDialogId;
    private Long categoryId;
    private Long oldFrontDialogId;
    private Long oldBackDialogId;
    private Long newFrontDialogId;
    private Long newBackDialogId;
}
