package com.fork.api.controllers;

import com.fork.api.enums.Role;
import com.fork.api.exceptions.AccessDeniedException;
import com.fork.api.exceptions.InvalidTokenException;
import com.fork.api.exceptions.UserNotFoundException;
import com.fork.api.models.Profit;
import com.fork.api.models.User;
import com.fork.api.repos.ForkRepos;
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
    ForkRepos forkRepos;

    @GetMapping("/get.users.stats")
    public ResponseEntity<Profit> getAllStats(
            @RequestParam String token
    ){
        User userByToken = userRepos.findByToken(token);
        if (userByToken != null) {
            if(userByToken.getRole().equals(Role.ADMIN)) {

                List<User> users = userRepos.findAll();
                Profit profit = new Profit();

                users.forEach(user -> {
                    Profit profitUser = new Profit(forkRepos.findAllByUser(user), user);
                    profit.setDay(profit.getDay() + profitUser.getDay());
                    profit.setWeek(profit.getWeek() + profitUser.getWeek());
                    profit.setMonth(profit.getMonth() + profitUser.getMonth());
                });

                return new ResponseEntity<>(profit, HttpStatus.OK);
            } else
                throw new AccessDeniedException();
        } else
            throw new InvalidTokenException();
    }

    @GetMapping("/get.users.statsList")
    public ResponseEntity<List<Profit>> getAllStatsList(
            @RequestParam String token
    ){
        User userByToken = userRepos.findByToken(token);
        if (userByToken != null) {
            if(userByToken.getRole().equals(Role.ADMIN)) {

                List<User> users = userRepos.findAll();
                List<Profit> profits = new ArrayList<>();

                users.forEach(user -> {
                    profits.add(new Profit(forkRepos.findAllByUser(user), user));
                });

                return new ResponseEntity<>(profits, HttpStatus.OK);
            } else
                throw new AccessDeniedException();
        } else
            throw new InvalidTokenException();
    }

    @GetMapping("/get.user.stats")
    public ResponseEntity<Profit> getSingleStats(
            @RequestParam String token,
            @RequestParam long id
    ){
        User userByToken = userRepos.findByToken(token);
        if (userByToken != null) {
            if(userByToken.getRole().equals(Role.ADMIN)) {

                User user = userRepos.findById(id);
                if(user != null) {


                    Profit profit = new Profit(forkRepos.findAllByUser(user), user);
                    return new ResponseEntity<>(profit, HttpStatus.OK);
                } else
                    throw new UserNotFoundException();
            } else
                throw new AccessDeniedException();
        } else
            throw new InvalidTokenException();
    }

    @GetMapping("/user.getStats")
    public ResponseEntity<Profit> getStats(
            @RequestParam String token
    ){
        User userByToken = userRepos.findByToken(token);
        if (userByToken != null) {

            Profit profit = new Profit(forkRepos.findAllByUser(userByToken), userByToken);

            return new ResponseEntity<>(profit, HttpStatus.OK);
        } else
            throw new InvalidTokenException();
    }
}
