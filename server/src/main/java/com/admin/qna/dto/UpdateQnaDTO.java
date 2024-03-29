package com.admin.qna.dto;

import lombok.Data;

@Data
public class UpdateQnaDTO {
    private Long qnaId;
    private String title;
    private String content;
}
