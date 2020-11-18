package com.fork.api.controllers;

import com.fork.api.enums.Role;
import com.fork.api.exceptions.AccessDeniedException;
import com.fork.api.exceptions.InvalidTokenException;
import com.fork.api.exceptions.UserNotFoundException;
import com.fork.api.models.Statistic;
import com.fork.api.models.User;
import com.fork.api.repos.ForkRepos;
import com.fork.api.repos.ProfitRepos;
import com.fork.api.repos.UserRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StatsController {

    @Autowired
    UserRepos userRepos;
    @Autowired
    ProfitRepos profitRepos;

    @GetMapping("/get.users.stats")
    public ResponseEntity<Statistic> getAllStats(
            @RequestParam String token
    ){
        User userByToken = userRepos.findByToken(token);
        if (userByToken != null) {
            if(userByToken.getRole().equals(Role.ADMIN)) {

                List<User> users = userRepos.findAll();
                Statistic statistic = new Statistic();

                users.forEach(user -> {
                    Statistic profitUser = new Statistic(profitRepos.findAllByUser(user), user);
                    statistic.setDay(statistic.getDay().add(profitUser.getDay()));
                    statistic.setWeek(statistic.getWeek().add(profitUser.getWeek()));
                    statistic.setMonth(statistic.getMonth().add(profitUser.getMonth()));
                });

                return new ResponseEntity<>(statistic, HttpStatus.OK);
            } else
                throw new AccessDeniedException();
        } else
            throw new InvalidTokenException();
    }

    @GetMapping("/get.users.statsList")
    public ResponseEntity<List<Statistic>> getAllStatsList(
            @RequestParam String token
    ){
        User userByToken = userRepos.findByToken(token);
        if (userByToken != null) {
            if(userByToken.getRole().equals(Role.ADMIN)) {

                List<User> users = userRepos.findAll();
                List<Statistic> stats = new ArrayList<>();

                users.forEach(user -> {
                    stats.add(new Statistic(profitRepos.findAllByUser(user), user));
                });

                return new ResponseEntity<>(stats, HttpStatus.OK);
            } else
                throw new AccessDeniedException();
        } else
            throw new InvalidTokenException();
    }

    @GetMapping("/get.user.stats")
    public ResponseEntity<Statistic> getSingleStats(
            @RequestParam String token,
            @RequestParam long id
    ){
        User userByToken = userRepos.findByToken(token);
        if (userByToken != null) {
            if(userByToken.getRole().equals(Role.ADMIN)) {

                User user = userRepos.findById(id);
                if(user != null) {


                    Statistic statistic = new Statistic(profitRepos.findAllByUser(user), user);
                    return new ResponseEntity<>(statistic, HttpStatus.OK);
                } else
                    throw new UserNotFoundException();
            } else
                throw new AccessDeniedException();
        } else
            throw new InvalidTokenException();
    }

    @GetMapping("/user.getStats")
    public ResponseEntity<Statistic> getStats(
            @RequestParam String token
    ){
        User userByToken = userRepos.findByToken(token);
        if (userByToken != null) {

            Statistic statistic = new Statistic(profitRepos.findAllByUser(userByToken), userByToken);

            return new ResponseEntity<>(statistic, HttpStatus.OK);
        } else
            throw new InvalidTokenException();
    }
}
