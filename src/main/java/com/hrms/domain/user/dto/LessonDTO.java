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
public class LessonDTO {
    @NotNull
    @Size(min = 2, max = 64)
    private String name;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate schedule;//课程开始时间
    @NotNull
    @Range(min = 1, max = 99)
    private Integer timePlan;//持续天数
    @NotNull
    @NotEmpty
    @Size(min = 2, max = 128)
    private String address;
    @NotEmpty
    @NotNull
    @Size(min = 2, max = 128)
    private String info;
}
