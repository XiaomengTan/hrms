package com.hrms.domain.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("notice_file")
public class NoticeFile {
    private Long id;
    private Long noticeId;
    private Long fileId;
    private Long frontId;
    private Long backId;
}
