package com.fork.api.controllers;

import com.fork.api.exceptions.AccessDeniedException;
import com.fork.api.exceptions.InvalidTokenException;
import com.fork.api.models.Bet;
import com.fork.api.models.BkAccount;
import com.fork.api.models.Fork;
import com.fork.api.models.User;
import com.fork.api.repos.BetRepos;
import com.fork.api.repos.BkAccountRepos;
import com.fork.api.repos.ForkRepos;
import com.fork.api.repos.UserRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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

    @PostMapping("/add.Fork")
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
}
