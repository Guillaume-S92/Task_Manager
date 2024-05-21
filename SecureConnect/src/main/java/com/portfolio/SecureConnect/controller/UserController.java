package com.portfolio.SecureConnect.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.portfolio.SecureConnect.dto.UserDto;
import com.portfolio.SecureConnect.service.UserService;

@Controller
public class UserController {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String getRegistrationPage(@ModelAttribute("user") UserDto userDto) {
        return "register";
    }

    @PostMapping("/registration")
    public String saveUser(@Validated @ModelAttribute("user") UserDto userDto, RedirectAttributes redirectAttributes) {
        try {
            userService.save(userDto);
            redirectAttributes.addFlashAttribute("message", "User registered successfully");
            return "redirect:/login";
        } catch (IllegalArgumentException e) {
            // Add the validation error to flash attributes
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            // Return current registration page to display error
            return "register";
        }
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }



    @GetMapping("/admin-page")
    @PreAuthorize("hasAuthority('ADMIN')") // Only allow users with "ADMIN" role
    public String adminPage(Model model, Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("user", userDetails);
        return "admin";
    }
}
