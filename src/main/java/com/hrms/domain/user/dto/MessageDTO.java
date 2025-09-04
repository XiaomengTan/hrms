package com.hrms.domain.user.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDTO {
    @NotNull
    private Byte receiverType;
    @NotNull
    private Long receiverId;
    @NotNull
    private Integer page;
}
