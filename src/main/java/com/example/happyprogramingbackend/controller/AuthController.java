package com.example.happyprogramingbackend.controller;


import com.example.happyprogramingbackend.Dto.Request.CheckEmailDto;
import com.example.happyprogramingbackend.Dto.Request.CreateUserDto;
import com.example.happyprogramingbackend.Dto.Request.LoginRequest;
import com.example.happyprogramingbackend.Service.AuthService;
import com.example.happyprogramingbackend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.authenticateUser(request));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody CreateUserDto request) {
        return ResponseEntity.ok(userService.add(request));
    }

    @GetMapping("/verify/{id}")
    @ResponseBody
    public ModelAndView verify(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("greeting");
        userService.verifyUser(id);
        return modelAndView;
    }

    @PostMapping("/check-email")
    public Boolean checkEmail(@RequestBody CheckEmailDto request) {
        return userService.getDetailByEmail(request.getEmail());
    }

    @PostMapping("/reset-password")
    public Boolean resetPassword(@RequestBody CheckEmailDto request) {
        return userService.resetPassword(request.getEmail());
    }
}
