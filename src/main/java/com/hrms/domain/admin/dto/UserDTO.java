package com.hrms.domain.admin.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    @NotNull
    @NotEmpty
    @Size(min = 2, max = 20)
    private String name;
    @NotNull
    @NotEmpty
    @Size(min = 8, max = 30)
    private String password;
    @NotNull
    private Integer worker;
}
