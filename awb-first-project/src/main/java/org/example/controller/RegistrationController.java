package org.example.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegistrationController {

    @GetMapping("/register-login")
    public ModelAndView showRegisterLoginPage(HttpServletResponse response) {
        Cookie jwtCookie = new Cookie("JWT", null);

        jwtCookie.setMaxAge(0);
        jwtCookie.setPath("/");
        response.addCookie(jwtCookie);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("register-login");
        return modelAndView;
    }

}
