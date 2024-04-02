package com.admin.qna.app.qna.dto;

import lombok.Data;

@Data
public class CreateQnaDTO {
    private String title;
    private String content;
    private String userId;
    private String productCode;
}
