package com.portfolio.TaskManager.service;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.portfolio.TaskManager.model.User;

public class CustomUserDetail implements UserDetails {

    private final User user;

    public CustomUserDetail(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // More robust handling of roles
        String role = user.getRole();
        if (role != null) {
            return Collections.singletonList(new SimpleGrantedAuthority(role));
        } else {
            return Collections.emptyList();
        }
    }

    public String getFullname() {
        return user.getFullname();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        // Implement logic to check account expiration
        // For now, return true for simplicity
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // Implement logic to check account lock status
        // For now, return true for simplicity
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // Implement logic to check password expiration
        // For now, return true for simplicity
        return true;
    }

    @Override
    public boolean isEnabled() {
        // Implement logic to check user enabled status
        // For now, return true for simplicity
        return true;
    }
}
