package com.fork.api.controllers;

import com.fork.api.enums.Role;
import com.fork.api.exceptions.BookmakerAlreadyExistsException;
import com.fork.api.exceptions.BookmakerNotFoundException;
import com.fork.api.exceptions.InvalidTokenException;
import com.fork.api.models.Bookmaker;
import com.fork.api.models.User;
import com.fork.api.repos.BookmakerRepos;
import com.fork.api.repos.UserRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookmakerController {

    @Autowired
    BookmakerRepos bookmakerRepos;
    @Autowired
    UserRepos userRepos;

    @GetMapping("/get.bookmaker")
    public ResponseEntity<Bookmaker> getBookmakerById(
            @RequestParam(value = "id") long id,
            @RequestParam(value = "token") String token
    ) {
        if(userRepos.findByToken(token) != null) {

            Bookmaker bookmaker = bookmakerRepos.findById(id);
            if(bookmaker != null) {

                return new ResponseEntity<>(bookmaker, HttpStatus.OK);
            } else
                throw new BookmakerNotFoundException();
        } else
            throw new InvalidTokenException();
    }

    @GetMapping("/get.bookmakers")
    public ResponseEntity<List<Bookmaker>> getBookmakerById(
            @RequestParam(value = "token") String token
    ) {
        if(userRepos.findByToken(token) != null) {

            List<Bookmaker> bookmakers = bookmakerRepos.getAll();
            return new ResponseEntity<>(bookmakers, HttpStatus.OK);
        } else
            throw new InvalidTokenException();
    }

    @PostMapping("/add.bookmaker")
    public ResponseEntity<Bookmaker> addBookmaker(
            @RequestParam String token,
            @RequestParam String title,
            @RequestParam String link
    ) {

        User user = userRepos.findByToken(token);
        if(user != null && user.getRole().equals(Role.ADMIN)) {

            Bookmaker bookmaker = bookmakerRepos.findByTitleOrLink(title, link);
            if(bookmaker == null) {

                bookmaker = new Bookmaker(title, link);
                bookmakerRepos.save(bookmaker);

                return new ResponseEntity<>(bookmaker, HttpStatus.OK);
            } else
                throw new BookmakerAlreadyExistsException();
        } else
            throw new InvalidTokenException();
    }
}
