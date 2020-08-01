package com.fork.api.controllers;

import com.fork.api.exceptions.AccessDeniedException;
import com.fork.api.models.Settings;
import com.fork.api.models.User;
import com.fork.api.repos.UserRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class SettingsController {

    @Autowired
    UserRepos userRepos;

    @PostMapping("/user.setSettings")
    public ResponseEntity<User> setSettings(
            //add new settings in future
            @RequestParam int balance_percent,
            HttpSession session
    ) {
        //check if user login
        if(session.getAttribute("userId") != null) {

            User user = userRepos.findById(
                    Long.parseLong(session.getAttribute("userId").toString())
            );

            Settings userSettings = user.getSettings();
            //base for edit more settings
            userSettings.setBalance_percent(Math.abs(balance_percent));

            user.setSettings(userSettings);
            userRepos.save(user);

            return new ResponseEntity<>(user, HttpStatus.OK);
        } else
            throw new AccessDeniedException();
    }
}
