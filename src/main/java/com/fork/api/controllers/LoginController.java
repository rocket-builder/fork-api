package com.fork.api.controllers;

import com.fork.api.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @GetMapping("/login")
    public ResponseEntity<User> login() {

        User user = new User("test", "test");
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
