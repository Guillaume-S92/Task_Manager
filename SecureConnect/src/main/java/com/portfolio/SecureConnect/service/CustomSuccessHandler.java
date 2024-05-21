package com.portfolio.SecureConnect.service;

import java.io.IOException;
import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String redirectUrl = determineRedirectUrl(authorities);

        response.sendRedirect(redirectUrl);
    }

    private String determineRedirectUrl(Collection<? extends GrantedAuthority> authorities) {

        String role = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElse("");

        switch (role) {
            case "ADMIN":
                return "/admin-page";
            case "USER":
                return "/home";
            default:
                return "/error";
        }
    }
}
