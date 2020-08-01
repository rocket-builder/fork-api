package com.fork.api.repos;


import com.fork.api.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepos extends CrudRepository<User, Long> {

    List<User> findAll();
    User findById (long id);
    User findByLogin (String login);
    User findByToken (String token);

}
