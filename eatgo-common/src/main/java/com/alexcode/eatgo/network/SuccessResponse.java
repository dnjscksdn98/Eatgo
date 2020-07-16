package com.alexcode.eatgo.network;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SuccessResponse<T> {

    private LocalDateTime transactionTime;

    private Integer status;

    private String resultCode;

    private String message;

    @JsonInclude(NON_NULL)
    private T data;

    public static <T> SuccessResponse<T> OK() {
        return (SuccessResponse<T>) SuccessResponse.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("OK")
                .message("OK")
                .build();
    }

    public static <T> SuccessResponse<T> OK(T data, Integer status, SuccessCode successCode) {
        return (SuccessResponse<T>) SuccessResponse.builder()
                .transactionTime(LocalDateTime.now())
                .status(status)
                .resultCode(successCode.getResultCode())
                .message(successCode.getMessage())
                .data(data)
                .build();
    }

    public static <T> SuccessResponse<T> CREATED(T data, Integer status, SuccessCode successCode) {
        return (SuccessResponse<T>) SuccessResponse.builder()
                .transactionTime(LocalDateTime.now())
                .status(status)
                .resultCode(successCode.getResultCode())
                .message(successCode.getMessage())
                .data(data)
                .build();
    }
}
