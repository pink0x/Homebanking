package com.mindhub.homebaking.repositories;

import com.mindhub.homebaking.models.Account;
import com.mindhub.homebaking.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AccountRepository extends JpaRepository<Account, Long> {

    boolean existsByNumber (String number);
    Account findByNumber (String number);
}
