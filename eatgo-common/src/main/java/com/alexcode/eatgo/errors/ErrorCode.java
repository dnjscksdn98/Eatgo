package com.alexcode.eatgo.errors;

public enum ErrorCode {

    INVALID_INPUT_VALUE(400, "V001", "Invalid Input Value");

    private final Integer status;
    private final String code;
    private final String message;

    ErrorCode(final Integer status, final String code, final String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public String getCode() {
        return this.code;
    }

    public Integer getStatus() {
        return this.status;
    }

}
