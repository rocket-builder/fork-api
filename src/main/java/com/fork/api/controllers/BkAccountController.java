package com.fork.api.controllers;

import com.fork.api.exceptions.*;
import com.fork.api.models.*;
import com.fork.api.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;

@RestController
public class BkAccountController {

    @Autowired
    UserRepos userRepos;
    @Autowired
    BookmakerRepos bookmakerRepos;
    @Autowired
    BkAccountRepos bkAccountRepos;
    @Autowired
    BetRepos betRepos;
    @Autowired
    ProfitRepos profitRepos;

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

    @GetMapping("/get.bkAccount")
    public ResponseEntity<BkAccount> addBkAccount(
            @RequestParam String token,
            @RequestParam long bk_account_id
    ) {
        User userByToken = userRepos.findByToken(token);
        if(userByToken != null) {

            BkAccount bkAccount = bkAccountRepos.findById(bk_account_id);
            if(bkAccount != null) {

                if(userByToken.getBk_accounts().contains(bkAccount)) {

                    return new ResponseEntity<>(bkAccount, HttpStatus.OK);
                } else
                    throw new AccessDeniedException();
            } else
                throw new BkAccountNotFoundException();
        } else
            throw new InvalidTokenException();
    }

    @GetMapping("/user.getActiveBkAccounts")
    public ResponseEntity<Set<BkAccount>> getActiveBkAccounts(
            @RequestParam String token
    ) {
        User userByToken = userRepos.findByToken(token);
        if(userByToken != null) {

            Set<BkAccount> bkAccounts = bkAccountRepos.findAllByIsActiveAndUser(true, userByToken);
            if(!bkAccounts.isEmpty()) {

                return new ResponseEntity<>(bkAccounts, HttpStatus.OK);
            } else
                throw new BkAccountNotFoundException();
        } else
            throw new InvalidTokenException();
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
            @RequestParam double balance
    ) {
        User userByToken = userRepos.findByToken(token);
        if(userByToken != null) {

            BkAccount bkAccount = bkAccountRepos.findById(bk_account_id);
            if(bkAccount != null) {

                if(userByToken.getBk_accounts().contains(bkAccount)) {

                    if(balance > 0) {
                        profitRepos.save(new Profit(balance - bkAccount.getBalance(), userByToken));

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

    @PostMapping("/user.setBkAccountActive")
    public ResponseEntity<User> bkAccountSetActive(
            @RequestParam String token,
            @RequestParam long bk_account_id,
            @RequestParam boolean isActive
    ) {
        User userByToken = userRepos.findByToken(token);
        if(userByToken != null) {

            BkAccount bkAccount = bkAccountRepos.findById(bk_account_id);
            if(bkAccount != null) {

                if(userByToken.getBk_accounts().contains(bkAccount)) {

                    bkAccount.setActive(isActive);
                    bkAccountRepos.save(bkAccount);

                    return new ResponseEntity<>(userByToken, HttpStatus.OK);
                } else
                    throw new AccessDeniedException();
            } else
                throw new BkAccountNotFoundException();
        } else
            throw new InvalidTokenException();
    }

    @RequestMapping(value = "/user.setBkAccountSettings", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> bkAccountSetSettings(
            @RequestParam String token,
            @RequestParam long bk_account_id,
            @RequestBody BkAccSettings settings
            ) {
        User userByToken = userRepos.findByToken(token);
        if(userByToken != null) {

            BkAccount bkAccount = bkAccountRepos.findById(bk_account_id);
            if(bkAccount != null) {

                if(userByToken.getBk_accounts().contains(bkAccount)) {

                    bkAccount.setSettings(settings);
                    bkAccountRepos.save(bkAccount);

                    return new ResponseEntity<>(userByToken, HttpStatus.OK);
                } else
                    throw new AccessDeniedException();
            } else
                throw new BkAccountNotFoundException();
        } else
            throw new InvalidTokenException();
    }
}
