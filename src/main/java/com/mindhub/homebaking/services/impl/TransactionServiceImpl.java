package com.mindhub.homebaking.services.impl;

import com.mindhub.homebaking.models.Transaction;
import com.mindhub.homebaking.repositories.TransactionRepository;
import com.mindhub.homebaking.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public void saveTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
    }


}