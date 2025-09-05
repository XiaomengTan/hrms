package com.hrms.domain.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkerDTO {
    @NotNull
    @NotEmpty
    @Size(min = 2, max = 20)
    private String name;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
    @NotNull
    @Range(min = 0, max = 1)
    private Integer socialSecurity;
    @NotNull
    @Size(min = 0, max = 1024)
    private String rpRecord;
    @NotNull
    @Size(min = 0, max = 1024)
    private String transfer;
    @NotNull
    @Range(min = 0, max = 1)
    private Integer workStatus;
    @NotNull
    @Size(min = 0, max = 1024)
    private String certificate;
    @NotNull
    @Range(min = 1, max = 3)
    private Integer laborStatus;
    @NotNull
    private Integer job;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate onboardingTime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate departureTime;
}
