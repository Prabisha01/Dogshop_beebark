package com.example.dogshop.Controller;

import com.example.dogshop.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping
public class LoginController {
    private final UserService userService;


    @GetMapping("/login")
    public String showLoginPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "/login";
        }

        return "redirect:/homepage";
    }
    @PostMapping("/logout")
    public String logout(Authentication authentication) {
        if (authentication.isAuthenticated()) {
            SecurityContextHolder.clearContext();
        }
        return "/login";
    }
}

