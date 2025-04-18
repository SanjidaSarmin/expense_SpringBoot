package com.example.demo.service;

import com.example.demo.entity.Expense;
import com.example.demo.entity.Groups;
import com.example.demo.entity.Transaction;
import com.example.demo.entity.Member;
import com.example.demo.repository.ExpenseRepository;
import com.example.demo.repository.GroupRepository;
import com.example.demo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExpenseService {
    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private GroupRepository groupRepository;

    // Get all expenses
    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    // Get expense by ID
    public Expense getExpenseById(Long id) {
        return expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found with id: " + id));
    }

    // Create a new expense
    public Expense createExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    // Update an existing expense
    public Expense updateExpense(Long id, Expense updatedExpense) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found with id: " + id));

//        expense.setDescription(updatedExpense.getDescription());
//        expense.setAmount(updatedExpense.getAmount());
//        expense.setDate(updatedExpense.getDate());
        // Set other fields as needed

        return expenseRepository.save(expense);
    }

    // Delete an expense by ID
    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }

    public Map<String, Double> splitExpense(Long expenseId) {
        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new RuntimeException("Expense not found"));

        Groups group = groupRepository.findById(expense.getGroup().getId())
                .orElseThrow(() -> new RuntimeException("Group not found"));

        List<Member> members = group.getMembers();
        System.out.println("Members count: " + members.size());

        System.out.println("Paid By: " + (expense.getPaidBy() != null ? expense.getPaidBy().getName() : "null"));
        System.out.println("Group: " + group.getName());
        System.out.println("Members count: " + members.size());

        double amount = expense.getAmount();
        double splitAmount = amount / members.size();

        Map<String, Double> debts = new HashMap<>();

        for (Member user : members) {
            System.out.println("Checking user: " + user.getName());

            if (expense.getPaidBy() == null || user.getId().equals(expense.getPaidBy().getId())) {
                continue;
            }

            String key = user.getName() + " owes " + expense.getPaidBy().getName();
            debts.put(key, splitAmount);
        }

        System.out.println("Final Debts: " + debts);

        return debts;
    }


    public Map<String, Double> splitExpenseAndSave(Long expenseId) {
        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new RuntimeException("Expense not found"));

        Groups group = expense.getGroup();
        List<Member> members = group.getMembers();

        double amount = expense.getAmount();
        double splitAmount = amount / members.size();

        Map<String, Double> debts = new HashMap<>();

        for (Member user : members) {
            if (!user.getId().equals(expense.getPaidBy().getId())) {
                // Save transaction
                Transaction transaction = new Transaction();
                transaction.setFromUser(user);
                transaction.setToUser(expense.getPaidBy());
                transaction.setAmount(splitAmount);
                transaction.setGroup(group);
                transactionRepository.save(transaction);

                // Add to response
                String key = user.getName() + " owes " + expense.getPaidBy().getName();
                debts.put(key, splitAmount);
            }
        }

        return debts;
    }

}
