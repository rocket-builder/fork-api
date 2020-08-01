package com.fork.api.repos;

import com.fork.api.models.Bookmaker;
import com.fork.api.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookmakerRepos extends CrudRepository<Bookmaker, Long> {

    Bookmaker findByLink(String link);
    Bookmaker findById(long id);

    Bookmaker findByTitleOrLink(String title, String link);
    List<Bookmaker> getAll();
}
