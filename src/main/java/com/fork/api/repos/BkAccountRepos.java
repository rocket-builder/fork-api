package com.fork.api.repos;

import com.fork.api.models.BkAccount;
import com.fork.api.models.Bookmaker;
import com.fork.api.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BkAccountRepos extends CrudRepository<BkAccount, Long> {

    BkAccount findById(long id);
    BkAccount findByLoginAndBookmaker(String login, Bookmaker bookmaker);

    List<BkAccount> findAllByIsActiveAndUser(boolean isActive, User user);
}

