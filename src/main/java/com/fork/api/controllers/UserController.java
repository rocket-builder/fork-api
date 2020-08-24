package com.fork.api.controllers;

import com.fork.api.Security;
import com.fork.api.enums.Role;
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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserRepos userRepos;
    @Autowired
    BookmakerRepos bookmakerRepos;
    @Autowired
    BkAccountRepos bkAccountRepos;

    @PostMapping("/add.user")
    public ResponseEntity<User> addUser(
            @RequestParam String token,
            @RequestParam String login,
            @RequestParam String password,
            @RequestParam String subscribe_end_date
    ) throws ParseException {

        User userAdmin = userRepos.findByToken(token);
        if(userAdmin != null && userAdmin.getRole().equals(Role.ADMIN)) {

            User userFromDb = userRepos.findByLogin(login);
            if(userFromDb == null) {

                User user = new User(
                        login,
                        Security.MD5(password),
                        new SimpleDateFormat("yyyy-MM-dd").parse(subscribe_end_date)
                );
                userRepos.save(user);

                return new ResponseEntity<>(user, HttpStatus.OK);
            } else
                throw new LoginTakenException();
        } else
            throw new InvalidTokenException();
    }

    @GetMapping("/get.user")
    public ResponseEntity<User> getUserById(
            @RequestParam(value = "id") long id,
            @RequestParam(value = "token") String token
    ) {
        User user = userRepos.findById(id);
        if(user != null) {

            User userByToken = userRepos.findByToken(token);
            if(
                user.equals(userByToken) ||
                (
                    userByToken != null &&
                    userByToken.getRole().equals(Role.ADMIN)
                )
            ) {

                return new ResponseEntity<>(user, HttpStatus.OK);
            } else
                throw new InvalidTokenException();
        } else
            throw new UserNotFoundException();
    }

    @GetMapping("/get.users")
    public ResponseEntity<List<User>> getUsers(
            @RequestParam(value = "token") String token
    ) {
        User userAdmin = userRepos.findByToken(token);
        if(userAdmin != null && userAdmin.getRole().equals(Role.ADMIN)) {

            List<User> users = userRepos.findAll();

            return new ResponseEntity<>(users, HttpStatus.OK);
        } else
            throw new InvalidTokenException();
    }

    @PostMapping("/delete.user")
    public ResponseEntity<String> deleteUser(
            @RequestParam String token,
            @RequestParam long user_id
    ) {

        User userAdmin = userRepos.findByToken(token);
        if(userAdmin != null && userAdmin.getRole().equals(Role.ADMIN)) {

            User userFromDb = userRepos.findById(user_id);
            if(userFromDb != null) {

                userRepos.delete(userFromDb);

                return new ResponseEntity<>("ok", HttpStatus.OK);
            } else
                throw new UserNotFoundException();
        } else
            throw new InvalidTokenException();
    }
}
