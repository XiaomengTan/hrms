package com.hrms.domain.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("group_role")
public class GroupRole {
    private Long id;
    private Long groupId;
    private String name;
    private Long frontId;
    private Long backId;
    private Byte ctrlRole;
    private Byte ctrlChannel;
    private Byte ctrlSettings;
    private Byte editNotice;
    private Byte deleteNotice;
    private Byte editName;
    private Byte editAvatar;
    private Byte editIntroduction;
    private Byte editExpose;
    private Byte editTest;
    private Byte editQuestion;
    private Byte editUser;
    private Byte assignChannel;
    private Byte addUser;
    private Byte deleteUser;
    private Byte banUser;
    private Byte verifyAuth;
    private Byte disbandGroup;
    private Byte spreadMsg;
    private Byte logVisit;

//    public GroupRole(String name) {
//        this.name = name;
//    }
}
