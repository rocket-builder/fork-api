package com.fork.api.repos;

import com.fork.api.models.BkAccount;
import com.fork.api.models.Bookmaker;
import com.fork.api.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

public interface BkAccountRepos extends CrudRepository<BkAccount, Long> {

    BkAccount findById(long id);
    BkAccount findByLoginAndBookmaker(String login, Bookmaker bookmaker);

    Set<BkAccount> findAllByIsActiveAndUser(boolean isActive, User user);
}

