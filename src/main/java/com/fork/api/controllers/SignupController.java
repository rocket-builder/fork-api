package com.fork.api.controllers;

import com.fork.api.Security;
import com.fork.api.exceptions.LoginTakenException;
import com.fork.api.models.User;
import com.fork.api.repos.UserRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@RestController
public class SignupController {

    @Autowired
    UserRepos userRepos;

    @PostMapping("/signup")
    public ResponseEntity<User> signup(
            @RequestParam String login,
            @RequestParam String password,
            @RequestParam String subscribe_end_date
    ) throws ParseException {

            User userFromDb = userRepos.findByLogin(login);
            if(userFromDb != null) {

                throw new LoginTakenException();
            } else {

                User user = new User(
                        login,
                        Security.MD5(password),
                        new SimpleDateFormat("yyyy-MM-dd").parse(subscribe_end_date)
                );
                userRepos.save(user);

                return new ResponseEntity<>(user, HttpStatus.OK);
            }
    }
}
