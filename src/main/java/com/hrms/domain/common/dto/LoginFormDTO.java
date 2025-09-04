package com.hrms.domain.common.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginFormDTO {
    @NotNull
    @Size(min = 2, max = 20)
    private String name;
    @NotNull
    @Size(min = 8, max = 30)
    private String password;
}
