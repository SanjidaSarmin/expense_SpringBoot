package com.example.demo.controller;

import com.example.demo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/split")
    public ResponseEntity<String> splitExpense(
            @RequestParam Long groupId,
            @RequestParam Long paidByUserId,
            @RequestParam double amount
    ) {
        transactionService.splitExpense(groupId, paidByUserId, amount);
        return ResponseEntity.ok("Expense split successfully");
    }
}

