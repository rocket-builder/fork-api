package com.fork.api.controllers;

import com.fork.api.Config;
import com.fork.api.models.Settings;
import com.fork.api.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;

@RestController
public class LoginController {

    @GetMapping("/login")
    public ResponseEntity<User> login() {

        User user = new User("test", "test", new Date((new java.util.Date()).getTime()));
        user.setSettings(
                new Settings(
                        user,
                        Config.BALANCE_PERCENT
                )
        );

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
