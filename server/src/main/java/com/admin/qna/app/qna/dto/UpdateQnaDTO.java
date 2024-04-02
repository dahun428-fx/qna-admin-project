package com.admin.qna.app.qna.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateQnaDTO {
    private String title;
    private String content;
}
