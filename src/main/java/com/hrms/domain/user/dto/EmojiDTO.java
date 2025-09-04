package com.hrms.domain.user.dto;

import com.hrms.domain.common.entity.Emoji;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmojiDTO {
    private Emoji oldEmoji;
    private Emoji newEmoji;
}
