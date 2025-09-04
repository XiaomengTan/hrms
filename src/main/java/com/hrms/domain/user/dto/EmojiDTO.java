package com.hrms.domain.user.dto;

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
