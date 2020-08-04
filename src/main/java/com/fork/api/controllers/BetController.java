package com.fork.api.controllers;

import com.fork.api.exceptions.AccessDeniedException;
import com.fork.api.exceptions.InvalidTokenException;
import com.fork.api.models.Bet;
import com.fork.api.models.BkAccount;
import com.fork.api.models.User;
import com.fork.api.repos.BetRepos;
import com.fork.api.repos.BkAccountRepos;
import com.fork.api.repos.UserRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@RestController
public class BetController {

    @Autowired
    UserRepos userRepos;
    @Autowired
    BkAccountRepos bkAccountRepos;
    @Autowired
    BetRepos betRepos;

    @PostMapping("/add.bet")
    public ResponseEntity<Bet> addBet(
            @RequestParam String token,
            @RequestParam long bk_account_id,
            @RequestParam String match_title,
            @RequestParam String team,
            @RequestParam float coefficient,
            @RequestParam float bet_sum,
            @RequestParam String bet_date
    ) throws ParseException {

        User userByToken = userRepos.findByToken(token);
        BkAccount bkAccount = bkAccountRepos.findById(bk_account_id);

        if(userByToken != null) {

            if(bkAccount.getUser().equals(userByToken)) {

                Bet bet = new Bet(
                        bkAccount,
                        match_title,
                        team,
                        coefficient,
                        bet_sum,
                        new SimpleDateFormat("yyyy-MM-dd").parse(bet_date)
                );

                betRepos.save(bet);

                return new ResponseEntity<>(bet, HttpStatus.OK);
            } else
                throw new AccessDeniedException();
        } else
            throw new InvalidTokenException();
    }
}
