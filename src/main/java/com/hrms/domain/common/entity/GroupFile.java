package com.hrms.domain.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("group_file")
public class GroupFile {
    private Long id;
    private Long groupId;
    private Long fileId;
    private Long frontId;
    private Long backId;
}
