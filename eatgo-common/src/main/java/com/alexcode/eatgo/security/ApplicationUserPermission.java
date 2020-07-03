package com.alexcode.eatgo.security;

public enum ApplicationUserPermission {

    USER_READ("user:read"),
    USER_WRITE("user:write"),

    RESTAURANT_READ("restaurant:read"),
    RESTAURANT_WRITE("restaurant:write"),

    REVIEW_READ("review:read"),
    REVIEW_WRITE("review:write"),

    MENUITEM_READ("menuitem:read"),
    MENUITEM_WRITE("menuitem:write"),

    CATEGORY_READ("category:read"),
    CATEGORY_WRITE("category:write"),

    REGION_READ("region:read"),
    REGION_WRITE("region:write"),

    RESERVATION_READ("reservation:read"),
    RESERVATION_WRITE("reservation:write");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return this.permission;
    }
}
