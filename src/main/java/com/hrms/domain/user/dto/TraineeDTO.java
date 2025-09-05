package com.hrms.domain.user.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TraineeDTO {
    @NotNull
    private Integer lesId;
    @NotNull
    @NotEmpty
    private List<Integer> workerIds;
}
