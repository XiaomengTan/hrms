package com.hrms.domain.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("friend_category")
public class FriendCategory {
    private Long id;
    @NotNull
    private Long userId;
    @NotEmpty
    @Pattern(regexp = "^.{1,16}$")
    private String name;
    //前一个分类的id，可以为null，表示当前元素是第一个元素
    private Long frontId;
    //前一个分类的id，可以为null，表示当前元素是第一个元素
    private Long backId;
}
