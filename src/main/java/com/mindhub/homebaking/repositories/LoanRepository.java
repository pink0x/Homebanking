package com.mindhub.homebaking.repositories;

import com.mindhub.homebaking.models.ClientLoan;
import com.mindhub.homebaking.models.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
    public interface LoanRepository extends JpaRepository<Loan, Long> {


    }


