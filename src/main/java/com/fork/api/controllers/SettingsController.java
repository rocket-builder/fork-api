package com.fork.api.controllers;

import com.fork.api.enums.EGame;
import com.fork.api.models.Game;
import com.fork.api.enums.EMarket;
import com.fork.api.exceptions.UserNotFoundException;
import com.fork.api.models.Market;
import com.fork.api.models.Settings;
import com.fork.api.models.User;
import com.fork.api.repos.UserRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class SettingsController {

    @Autowired
    UserRepos userRepos;

    @RequestMapping(value = "/user.setSettings", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> setSettings(
            //add new settings in future
            @RequestParam String token,
            @RequestBody Settings settings
    ) {

        User user = userRepos.findByToken(token);
        if(user != null) {

            user.setSettings(settings);
            userRepos.save(user);

            return new ResponseEntity<>(user, HttpStatus.OK);
        } else
            throw new UserNotFoundException();
    }

    @GetMapping("/settings.getGames")
    public ResponseEntity<List<EGame>> getGames(
            @RequestParam String token
    ){
        User user = userRepos.findByToken(token);
        if(user != null) {

            List<EGame> games = Arrays.asList(EGame.values());
            return new ResponseEntity<>(games, HttpStatus.OK);
        } else
            throw new UserNotFoundException();
    }

    @GetMapping("/settings.getMarkets")
    public ResponseEntity<List<EMarket>> getMarkets(
            @RequestParam String token
    ){
        User user = userRepos.findByToken(token);
        if(user != null) {

            List<EMarket> markets = Arrays.asList(EMarket.values());
            return new ResponseEntity<>(markets, HttpStatus.OK);
        } else
            throw new UserNotFoundException();
    }
}
