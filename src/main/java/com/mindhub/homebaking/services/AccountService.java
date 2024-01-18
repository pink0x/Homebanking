package com.mindhub.homebaking.services;

import com.mindhub.homebaking.models.Account;

public interface AccountService {

    void saveAccount(Account account);

    boolean existsByNumber (String number);
    Account findByNumber (String number);
}
