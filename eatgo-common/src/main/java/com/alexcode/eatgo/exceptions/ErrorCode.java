package com.alexcode.eatgo.exceptions;

public enum ErrorCode {

    // Common
    INVALID_INPUT_VALUE(400, "C001", "Invalid input value"),
    ENTITY_NOT_FOUND(404, "C002", "Entity not found"),

    // User
    INVALID_LOGIN_VALUE(400, "U001", "Login input value is invalid"),
    EMAIL_DUPLICATION(400, "U002", "This email already exists"),

    // Region
    REGION_DUPLICATION(400, "R001", "This region already exists"),

    // Category
    CATEGORY_DUPLICATION(400, "CA001", "This category already exists");

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
