package com.hrms.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NtcViewerConfigDTO {
    private NtcViewerConfig ntcViewerConfig;
    private List<NtcConfigMember> ntcConfigMembers;
}
