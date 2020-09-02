package com.fork.api.controllers;

import com.fork.api.enums.Role;
import com.fork.api.exceptions.AccessDeniedException;
import com.fork.api.exceptions.BkAccountNotFoundException;
import com.fork.api.exceptions.InvalidTokenException;
import com.fork.api.models.*;
import com.fork.api.repos.BetRepos;
import com.fork.api.repos.BkAccountRepos;
import com.fork.api.repos.ForkRepos;
import com.fork.api.repos.UserRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
public class ForkController {

    @Autowired
    ForkRepos forkRepos;
    @Autowired
    BetRepos betRepos;
    @Autowired
    BkAccountRepos bkAccountRepos;
    @Autowired
    UserRepos userRepos;

    @PostMapping("/add.fork")
    public ResponseEntity<Fork> addFork(
            @RequestParam String token,

            @RequestParam long left_bk_account_id,
            @RequestParam String left_match_title,
            @RequestParam String left_team,
            @RequestParam float left_coefficient,
            @RequestParam float left_bet_sum,
            @RequestParam String left_bet_date,

            @RequestParam long right_bk_account_id,
            @RequestParam String right_match_title,
            @RequestParam String right_team,
            @RequestParam float right_coefficient,
            @RequestParam float right_bet_sum,
            @RequestParam String right_bet_date
    ) throws ParseException {

        User userByToken = userRepos.findByToken(token);
        if (userByToken != null) {

            BkAccount leftBkAccount = bkAccountRepos.findById(left_bk_account_id);
            BkAccount rightBkAccount = bkAccountRepos.findById(right_bk_account_id);
            if (leftBkAccount.getUser().equals(userByToken) && rightBkAccount.getUser().equals(userByToken)) {

                Bet betLeft = new Bet(
                        leftBkAccount,
                        left_match_title,
                        left_team,
                        left_coefficient,
                        left_bet_sum,
                        new SimpleDateFormat("yyyy-MM-dd").parse(left_bet_date)
                );

                Bet betRight = new Bet(
                        rightBkAccount,
                        right_match_title,
                        right_team,
                        right_coefficient,
                        right_bet_sum,
                        new SimpleDateFormat("yyyy-MM-dd").parse(right_bet_date)
                );

                betRepos.save(betLeft);
                betRepos.save(betRight);

                Fork fork = new Fork(betLeft, betRight);
                forkRepos.save(fork);

                return new ResponseEntity<>(fork, HttpStatus.OK);
            } else
                throw new AccessDeniedException();
        } else
            throw new InvalidTokenException();
    }

    @GetMapping("/user.getForks")
    public ResponseEntity<List<Fork>> getForks(
            @RequestParam String token
    ){
        User userByToken = userRepos.findByToken(token);
        if (userByToken != null) {

            List<Fork> forks = forkRepos.findAllByUser(userByToken);

            return new ResponseEntity<>(forks, HttpStatus.OK);
        } else
            throw new InvalidTokenException();
    }

    @GetMapping("/bkAccount.getForks")
    public ResponseEntity<List<Fork>> getForksSingle(
            @RequestParam String token,
            @RequestParam long bk_account_id
    ){
        User userByToken = userRepos.findByToken(token);
        if (userByToken != null) {

            BkAccount bkAccount = bkAccountRepos.findById(bk_account_id);
            if (bkAccount != null){

                List<Fork> forks = forkRepos.findAllByLeftBkAccIdOrRightBkAccId(bkAccount.getId(), bkAccount.getId());

                return new ResponseEntity<>(forks, HttpStatus.OK);
            } else
                throw new BkAccountNotFoundException();
        } else
            throw new InvalidTokenException();
    }

    @GetMapping("/get.users.forks")
    public ResponseEntity<List<Fork>> getAllForks(
            @RequestParam String token
    ){
        User userByToken = userRepos.findByToken(token);
        if (userByToken != null) {
            if(userByToken.getRole().equals(Role.ADMIN)) {

                List<User> users = userRepos.findAll();
                List<Fork> forks = new ArrayList<>();

                users.forEach(user -> {
                    forks.addAll(forkRepos.findAllByUser(user));
                });

                forks.sort((o1, o2) -> (int) (o1.getId() - o2.getId()));

                return new ResponseEntity<>(forks, HttpStatus.OK);
            } else
                throw new AccessDeniedException();
        } else
            throw new InvalidTokenException();
    }
}
