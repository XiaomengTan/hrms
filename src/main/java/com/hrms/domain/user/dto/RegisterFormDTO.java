package com.hrms.domain.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterFormDTO {
    @Email
    @NotEmpty
    @Pattern(regexp = "^.{1,64}$")
    private String email;
    @NotEmpty
    @Pattern(regexp = "^.{8,32}$")
    private String password;
    @NotEmpty
    @Pattern(regexp = "^.{8,32}$")
    private String confirmPass;
    @NotEmpty
    @Pattern(regexp = "^.{6}$")
    private String authCode;
    @NotNull
    private boolean agreeState;
}
