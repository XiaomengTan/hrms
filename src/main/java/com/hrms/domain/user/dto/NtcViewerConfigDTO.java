package com.hrms.domain.user.dto;

import com.hrms.domain.common.entity.NtcConfigMember;
import com.hrms.domain.common.entity.NtcViewerConfig;
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
