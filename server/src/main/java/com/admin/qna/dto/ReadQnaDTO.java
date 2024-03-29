package com.admin.qna.dto;

import java.security.Timestamp;

import lombok.Data;

@Data
public class ReadQnaDTO {
    private Long qnaId;
    private String title;
    private String content;
    private Timestamp regDate;
    private Timestamp latestUpdateDate;
    private String userId;
    private String productCode;
    private String isDeleted;
}
