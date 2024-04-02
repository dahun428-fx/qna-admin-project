package com.admin.qna.errors.response;

import java.util.List;
import org.springframework.validation.FieldError;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class ErrorResponse {
    
    private final String code;
    private final String message;

    private final List<ValidationError> errors;

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class ValidationError {
        private final String field;
        private final String message;

        public static ValidationError of(final FieldError fieldError) {
            return ValidationError
                        .builder()
                        .field(fieldError.getField())
                        .message(fieldError.getDefaultMessage())
                        .build();
        }
    }
}
