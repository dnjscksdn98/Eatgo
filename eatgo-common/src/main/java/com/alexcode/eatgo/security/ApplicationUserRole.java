package com.alexcode.eatgo.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static com.alexcode.eatgo.security.ApplicationUserPermission.*;

public enum ApplicationUserRole {

    CUSTOMER(Sets.newHashSet(
            RESTAURANT_READ,
            REVIEW_READ,
            REVIEW_WRITE,
            MENUITEM_READ,
            CATEGORY_READ,
            REGION_READ,
            RESERVATION_WRITE
    )),

    OWNER(Sets.newHashSet(
            RESERVATION_READ
    )),

    ADMIN(Sets.newHashSet(
            USER_READ,
            USER_WRITE,
            RESTAURANT_READ,
            RESTAURANT_WRITE,
            REVIEW_READ,
            MENUITEM_READ,
            MENUITEM_WRITE,
            CATEGORY_READ,
            CATEGORY_WRITE,
            REGION_READ,
            REGION_WRITE
    ));

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return this.permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
//        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
//                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
//                .collect(Collectors.toSet());
//        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));

        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }

}
