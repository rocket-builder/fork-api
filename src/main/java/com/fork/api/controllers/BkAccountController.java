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
    public ResponseEntity<BkAccount> addBkAccount(
            @RequestParam String bkUrl,
            @RequestParam String login,
            @RequestParam String password,
            @RequestParam String token
    ) {
            User user = userRepos.findByToken(token);
            if(user != null) {

                Bookmaker bookmaker = bookmakerRepos.findByLink(bkUrl);
                if(bookmaker != null) {

                    BkAccount bkAccountFromDb = bkAccountRepos.findByLoginAndBookmaker(login, bookmaker);
                    if(bkAccountFromDb == null) {

                        BkAccount bkAccount = new BkAccount(user, bookmaker, login, password);
                        bkAccountRepos.save(bkAccount);

                        return new ResponseEntity<>(bkAccount, HttpStatus.OK);
                    } else
                        throw new BkAccountAlreadyExistsException();
                } else
                    throw new BookmakerNotFoundException();
            } else
                throw new UserNotFoundException();
    }
    
    @PostMapping("/user.deleteBkAccount")
    public ResponseEntity<String> deleteBkAccount(
            @RequestParam long bk_account_id,
            @RequestParam String token
    ) {
            User user = userRepos.findByToken(token);
            if(user != null) {

                BkAccount bkAccountFromDb = bkAccountRepos.findById(bk_account_id);
                if(bkAccountFromDb != null) {

                    if(bkAccountFromDb.getUser().equals(user)) {

                        bkAccountRepos.delete(bkAccountFromDb);
                        return new ResponseEntity<>("ok", HttpStatus.OK);
                    } else
                        throw new AccessDeniedException();
                } else
                    throw new BkAccountNotFoundException();
            } else
                throw new UserNotFoundException();
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
