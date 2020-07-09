package com.alexcode.eatgo.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 예외 발생시 응답 객체
 *
 * @status: 상태 코드
 * @code: 커스텀 에러 코드
 * @message: 에러 코드 메시지
 * @errors: 재정의한 FieldError
 * 
 */

@Getter
@NoArgsConstructor
public class ErrorResponse {

    private LocalDateTime transactionTime;
    private Integer status;
    private String resultCode;
    private String message;
    private List<FieldError> errors;

    private ErrorResponse(final ErrorCode resultCode) {
        this.transactionTime = LocalDateTime.now();
        this.status = resultCode.getStatus();
        this.resultCode = resultCode.getCode();
        this.message = resultCode.getMessage();
        this.errors = new ArrayList<>();
    }

    private ErrorResponse(final ErrorCode resultCode, final List<FieldError> errors) {
        this.transactionTime = LocalDateTime.now();
        this.status = resultCode.getStatus();
        this.resultCode = resultCode.getCode();
        this.message = resultCode.getMessage();
        this.errors = errors;
    }

    public static ErrorResponse of(final ErrorCode code, final BindingResult bindingResult) {
        return new ErrorResponse(code, FieldError.of(bindingResult));
    }

    public static ErrorResponse of(final ErrorCode code) {
        return new ErrorResponse(code);
    }

    public static ErrorResponse of(final ErrorCode code, final List<FieldError> errors) {
        return new ErrorResponse(code, errors);
    }

    /**
     * 예외 발생된 필드의 상세 내용
     * 
     * @field: 예외 발생 필드
     * @value: 거부된 값
     * @reason: 예외 발생 디폴트 메시지
     */

    @Getter
    @NoArgsConstructor
    public static class FieldError {
        private String field;
        private String value;
        private String reason;

        private FieldError(final String field, final String value, final String reason) {
            this.field = field;
            this.value = value;
            this.reason = reason;
        }

        private static List<FieldError> of(final BindingResult bindingResult) {
            final List<org.springframework.validation.FieldError> fieldErrors = bindingResult.getFieldErrors();
            return fieldErrors.stream()
                    .map(error -> new FieldError(
                            error.getField(),
                            error.getRejectedValue() == null ? "" : error.getRejectedValue().toString(),
                            error.getDefaultMessage()))
                    .collect(Collectors.toList());
        }
    }
}
