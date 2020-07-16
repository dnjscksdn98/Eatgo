package com.alexcode.eatgo.security;

import com.alexcode.eatgo.domain.models.User;
import com.alexcode.eatgo.domain.status.UserStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class ApplicationUser implements UserDetails {

    private final User user;

    public ApplicationUser(User user) {
        this.user = user;
    }

    public Long getUserId() {
        return this.user.getId();
    }

    public Long getRestaurantId() {
        if(this.user.isRestaurantOwner()) {
            return this.user.getRestaurant().getId();
        }
        return null;
    }

    public String getName() {
        return this.user.getName();
    }

    public String getStatus() {
        return this.user.getStatus().name();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.user.getRole().getGrantedAuthorities();
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.user.isActive();
    }

}
