package com.example.Academy.service;

import com.example.Academy.Entity.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface TransactionService {

    List<Transaction> getAllTransaction();
    Optional<Transaction> getTransactionById(Long id);
    Transaction createTransaction(Transaction transaction);
    Optional<Transaction> deleteTransactionById(Long id);
    Optional<Transaction> updateById(Long id);
}
