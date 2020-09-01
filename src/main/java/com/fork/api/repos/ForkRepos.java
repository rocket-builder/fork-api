package com.fork.api.repos;

import com.fork.api.models.BkAccount;
import com.fork.api.models.Fork;
import com.fork.api.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ForkRepos extends CrudRepository<Fork, Long> {

    List<Fork> findAllByBkAccount_User(User user);
    List<Fork> findAllByBkAccount(BkAccount bkAccount);
}
