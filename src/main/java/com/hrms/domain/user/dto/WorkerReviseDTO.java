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
public class WorkerReviseDTO {
    @NotNull
    private Integer id;
    @Size(min = 2, max = 20)
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
    @Range(min = 0, max = 1)
    private Integer socialSecurity;
    @Size(min = 0, max = 1024)
    private String rpRecord;
    @Size(min = 0, max = 1024)
    private String transfer;
    @Range(min = 0, max = 1)
    private Integer workStatus;
    @Size(min = 0, max = 1024)
    private String certificate;
    @Range(min = 1, max = 3)
    private Integer laborStatus;
    private Integer job;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate onboardingTime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate departureTime;
}
