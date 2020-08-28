package com.fork.api.repos;

import com.fork.api.models.BkAccount;
import com.fork.api.models.Fork;
import org.springframework.data.repository.CrudRepository;

public interface ForkRepos extends CrudRepository<Fork, Long> {


}
