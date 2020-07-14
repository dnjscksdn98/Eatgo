package com.alexcode.eatgo.domain.network;

public enum SuccessCode {

    // Common
    OK("C001", "Successfully read data"),

    // User
    USER_ADMISSION_SUCCESS("U001", "Successfully registered"),

    // Reservation
    RESERVATION_SUCCESS("R001", "Successfully reserved"),

    // Review
    REVIEW_SUCCESS("RV001", "Successfully reviewed a restaurant");

    private final String resultCode;
    private final String message;

    SuccessCode(final String resultCode, final String message) {
        this.resultCode = resultCode;
        this.message = message;
    }

    public String getResultCode() {
        return this.resultCode;
    }

    public String getMessage() {
        return this.message;
    }
}
