package com.example.Academy.controller;

import com.example.Academy.Entity.Book;
import com.example.Academy.Entity.Transaction;
import com.example.Academy.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    @Autowired
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public List<Transaction> getAllTransaction() {
        return transactionService.getAllTransaction();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Long id) {
        Optional<Transaction> transaction = transactionService.getTransactionById(id);
        return transaction.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }

    @PostMapping
    public Transaction createTransaction(@RequestBody Transaction transaction) {
        return transactionService.createTransaction(transaction);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransactionById(@PathVariable Long id) {
        Optional<Transaction> transaction = transactionService.deleteTransactionById(id);
        if (transaction.isPresent()) {
            transactionService.deleteTransactionById(id);
            return ResponseEntity.notFound().build();
        } else {

            return ResponseEntity.noContent().build();
        }
    }

        @PutMapping("/{id}")
        public ResponseEntity<Transaction> updateById (@PathVariable Long id, @RequestBody Transaction transaction){
            Optional<Transaction> transaction1 = transactionService.updateById(id);
            return transaction1.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        }

    }