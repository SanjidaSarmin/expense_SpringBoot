package com.example.demo.controller;

import com.example.demo.dto.ExpenseRequest;
import com.example.demo.entity.Expense;
import com.example.demo.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @PostMapping
    public ResponseEntity<Expense> createExpense(@RequestBody ExpenseRequest request) {
        Expense expense = expenseService.createExpense(
                request.getGroupId(),
                request.getDescription(),
                request.getTotalAmount(),
                request.getPaidBy(),
                request.getSplits()
        );
        return ResponseEntity.ok(expense);
    }

    @GetMapping("/{groupId}")
    public ResponseEntity<List<Expense>> getExpensesByGroup(@PathVariable Long groupId) {
        return ResponseEntity.ok(expenseService.getAllExpensesByGroup(groupId));
    }
}
