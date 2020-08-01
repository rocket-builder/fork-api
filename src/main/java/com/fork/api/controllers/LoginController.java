package com.fork.api.controllers;

import com.fork.api.Config;
import com.fork.api.Security;
import com.fork.api.exceptions.IncorrectPasswordException;
import com.fork.api.exceptions.UserNotFoundException;
import com.fork.api.models.Settings;
import com.fork.api.models.User;
import com.fork.api.repos.UserRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.sql.Date;

@RestController
public class LoginController {

    @Autowired
    UserRepos userRepos;

    @PostMapping("/login")
    public ResponseEntity<User> login(
            @RequestParam String login,
            @RequestParam String password,
            HttpSession session
    ) {

        User userFromDb = userRepos.findByLogin(login);
        if(userFromDb != null) {

            if(userFromDb.getPassword().equals(Security.MD5(password))) {

                //create user session
                session.setAttribute("userId", userFromDb.getId());
                return new ResponseEntity<>(userFromDb, HttpStatus.OK);
            } else
                throw new IncorrectPasswordException();
        } else
            throw new UserNotFoundException();
    }

}
