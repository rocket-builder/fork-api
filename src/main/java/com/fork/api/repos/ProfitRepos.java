package com.fork.api.repos;

import com.fork.api.models.Profit;
import com.fork.api.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProfitRepos extends CrudRepository<Profit, Long> {

    List<Profit> findAllByUser(User user);
}
