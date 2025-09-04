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
    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    private LocalDateTime birthday;
    private String certificate;
    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    private LocalDateTime departureTime;
    private Integer id;
    private Integer job;
    private Integer laborStatus;
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    private LocalDateTime onboardingTime;
    private String rpRecord;
    private Integer socialSecurity;
    private String transfer;
    private Integer workStatus;
}
