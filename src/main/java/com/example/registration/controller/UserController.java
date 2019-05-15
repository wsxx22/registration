package com.example.registration.controller;

import com.example.registration.entity.User;
import com.example.registration.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public String registerUser (@RequestBody User user) {
        userService.registerUser(user);
        return user.getActivationToken().getValue();
    }

    @GetMapping("/activate") // /activate?token=wiefbwifbiwb123
    public String activeUser(@RequestParam("token") String token) {
        return userService.activationUser(token);
    }


}
