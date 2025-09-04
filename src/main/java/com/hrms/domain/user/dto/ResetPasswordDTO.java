package com.hrms.domain.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResetPasswordDTO {
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    @Pattern(regexp = "^.{8,32}$")
    private String password;
    @NotEmpty
    @Pattern(regexp = "^[a-fA-F0-9]{64}$")
    private String authCode;
}
