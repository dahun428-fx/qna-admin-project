package com.admin.qna.errors.exception;

import com.admin.qna.errors.errorcode.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RestApiException extends RuntimeException {
    
    private final ErrorCode errorCode;
}
