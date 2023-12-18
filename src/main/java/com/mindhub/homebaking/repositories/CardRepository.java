package com.mindhub.homebaking.repositories;

import com.mindhub.homebaking.models.Account;
import com.mindhub.homebaking.models.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CardRepository extends JpaRepository<Card, Long> {
}
