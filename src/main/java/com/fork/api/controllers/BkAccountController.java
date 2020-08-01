package com.fork.api.controllers;

import com.fork.api.exceptions.*;
import com.fork.api.models.BkAccount;
import com.fork.api.models.Bookmaker;
import com.fork.api.models.User;
import com.fork.api.repos.BkAccountRepos;
import com.fork.api.repos.BookmakerRepos;
import com.fork.api.repos.UserRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class BkAccountController {

    @Autowired
    UserRepos userRepos;
    @Autowired
    BookmakerRepos bookmakerRepos;
    @Autowired
    BkAccountRepos bkAccountRepos;

    @PostMapping("/user.addBkAccount")
    public ResponseEntity<User> addBkAccount(
            @RequestParam String bkUrl,
            @RequestParam String login,
            @RequestParam String password,
            HttpSession session
    ) {
        //check if user login
        if(session.getAttribute("userId") != null) {

            User user = userRepos.findById(
                    Long.parseLong(session.getAttribute("userId").toString())
            );
            if(user != null) {

                Bookmaker bookmaker = bookmakerRepos.findByLink(bkUrl);
                if(bookmaker != null) {

                    user.getBk_accounts().add(
                            new BkAccount(user, bookmaker, login, password)
                    );

                    userRepos.save(user);
                    return new ResponseEntity<>(user, HttpStatus.OK);
                } else
                    throw new BookmakerNotFoundException();
            } else
                throw new UserNotFoundException();
        } else
            throw new AccessDeniedException();
    }

    @PostMapping("/user.setBkAccountBalance")
    public ResponseEntity<User> bkAccountSetBalance(
            @RequestParam String token,
            @RequestParam long bk_account_id,
            @RequestParam float balance
    ) {
        User userByToken = userRepos.findByToken(token);
        if(userByToken != null) {

            BkAccount bkAccount = bkAccountRepos.findById(bk_account_id);
            if(bkAccount != null) {

                if(userByToken.getBk_accounts().contains(bkAccount)) {

                    if(balance > 0) {

                        bkAccount.setBalance(balance);
                        bkAccountRepos.save(bkAccount);

                        return new ResponseEntity<>(userByToken, HttpStatus.OK);
                    } else
                        throw new NegativeBalanceException();
                } else
                    throw new AccessDeniedException();
            } else
                throw new BkAccountNotFoundException();
        } else
            throw new InvalidTokenException();
    }
}
