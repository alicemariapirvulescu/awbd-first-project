package org.example.controller;

import org.example.controller.payload.request.LoginDto;
import org.example.controller.payload.request.RegisterDto;
import org.example.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/duolingo/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(Model model, @RequestBody LoginDto loginDto) {
        String jwt = userService.processLogin(loginDto);
        return ResponseEntity.ok().body(jwt);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterDto registerDto) {
        userService.register(registerDto);
        return ResponseEntity.ok().body("User registered successfully");
    }



}
