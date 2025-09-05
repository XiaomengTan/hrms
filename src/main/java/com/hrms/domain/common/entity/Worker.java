package com.hrms.domain.common.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * Worker
 */
@Data
public class Worker {
    private Integer id;
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime birthday;
    private Integer socialSecurity;
    private String rpRecord;
    private String transfer;
    private Integer workStatus;
    private String certificate;
    private Integer laborStatus;
    private Integer job;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime onboardingTime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime departureTime;
}
