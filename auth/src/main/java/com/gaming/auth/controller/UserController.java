package com.gaming.auth.controller;


import com.gaming.auth.model.User;
import com.gaming.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @PostMapping("/authenticate")
    public boolean authenticateUser(@RequestParam String username, @RequestParam String password) {
        return userService.authenticateUser(username, password);
    }
}
