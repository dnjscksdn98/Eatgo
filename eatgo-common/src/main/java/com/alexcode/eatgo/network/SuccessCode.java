package com.alexcode.eatgo.network;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SuccessCode {

    // Common
    OK("C001", "Successfully read data"),

    // User
    USER_ADMISSION_SUCCESS("U001", "Successfully registered"),
    USER_CREATION_SUCCESS("U002", "Successfully created a new user"),
    USER_UPDATE_SUCCESS("U003", "Successfully updated user"),
    USER_DEACTIVATION_SUCCESS("U004", "Successfully deactivated user"),

    // Restaurant
    RESTAURANT_CREATION_SUCCESS("RE001", "Successfully created a new restaurant"),
    RESTAURANT_UPDATE_SUCCESS("RE002", "Successfully updated restaurant"),

    // Menu item
    MENU_CREATION_SUCCESS("M001", "Successfully created a new menu"),
    MENU_UPDATE_SUCCESS("M002", "Successfully updated menu"),

    // Category
    CATEGORY_CREATION_SUCCESS("C001", "Successfully created a new category"),
    CATEGORY_UPDATE_SUCCESS("C002", "Successfully updated category"),
    CATEGORY_DELETE_SUCCESS("C003", "Successfully deleted category"),

    // Reservation
    RESERVATION_SUCCESS("R001", "Successfully reserved"),
    RESERVATION_ACCEPT("R002", "Accepted a reservation"),
    RESERVATION_REFUSE("R003", "Refused a reservation"),

    // Review
    REVIEW_SUCCESS("RV001", "Successfully reviewed a restaurant"),

    // Region
    REGION_CREATION_SUCCESS("RG001", "Successfully created a new region");

    private final String resultCode;
    private final String message;

}
