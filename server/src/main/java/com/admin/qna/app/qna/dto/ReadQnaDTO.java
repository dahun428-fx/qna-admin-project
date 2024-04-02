package com.admin.qna.app.qna.dto;

import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
