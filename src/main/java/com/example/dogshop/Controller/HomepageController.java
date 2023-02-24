package com.example.dogshop.Controller;

import com.example.dogshop.Pojo.ContactPojo;
import com.example.dogshop.Pojo.UserPojo;
import com.example.dogshop.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;


@Controller
@RequiredArgsConstructor
@RequestMapping("/homepage")
public class HomepageController {
    private final UserService userService;



}
