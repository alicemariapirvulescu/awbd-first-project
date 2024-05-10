package org.example.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.example.controller.payload.request.LoginDto;
import org.example.controller.payload.request.RegisterDto;
import org.example.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/duolingo/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("registerDto", new RegisterDto("", "", ""));
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute @Valid RegisterDto registerDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            // Return the form view if there are errors
            return "register";
        }

        userService.register(registerDto);
        model.addAttribute("message", "User registered successfully");
        return "registrationSuccess";
    }


    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("loginDto", new LoginDto("",""));
        return "login";  // login.html Thymeleaf template
    }

    @PostMapping("/login")
    public String loginUser(@Valid @ModelAttribute("loginDto") LoginDto loginDto, BindingResult result, Model model, HttpServletResponse response) {
        if (result.hasErrors()) {
            return "login";
        }

        String jwt = userService.processLogin(loginDto);
        // Create a cookie and set the JWT as its value
        Cookie jwtCookie = new Cookie("JWT", jwt);
        jwtCookie.setHttpOnly(true);
        jwtCookie.setPath("/");
        jwtCookie.setSecure(true);  // Set secure to true if using HTTPS
        response.addCookie(jwtCookie);

        return "redirect:/duolingo/language/all";  // Redirect to a secure page
    }}


