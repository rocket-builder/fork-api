package com.fork.api.repos;

import com.fork.api.models.Bet;
import com.fork.api.models.BkAccount;
import org.springframework.data.repository.CrudRepository;

public interface BetRepos extends CrudRepository<Bet, Long> {


}