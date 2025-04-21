package com.example.demo.service;

import com.example.demo.dto.ExpenseSplitRequest;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ExpenseService {
    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private ExpenseShareRepository expenseShareRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private MemberRepository memberRepository;

    public Expense createExpense(Long groupId, String description, Double totalAmount, Long paidById, List<ExpenseSplitRequest> splits) {
        Groups group = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found"));

        Member paidBy = memberRepository.findById(paidById)
                .orElseThrow(() -> new RuntimeException("PaidBy member not found"));

        Expense expense = new Expense();
        expense.setDescription(description);
        expense.setTotalAmount(totalAmount);
        expense.setGroup(group);
        expense.setPaidBy(paidBy);
        expense.setDate(LocalDate.now());

        List<ExpenseShare> shares = new ArrayList<>();
        for (ExpenseSplitRequest split : splits) {
            Member member = memberRepository.findById(split.getMemberId())
                    .orElseThrow(() -> new RuntimeException("Member not found: " + split.getMemberId()));

            ExpenseShare share = new ExpenseShare();
            share.setExpense(expense);
            share.setMember(member);
            share.setAmountOwed(split.getAmountOwed());

            shares.add(share);
        }

        expense.setShares(shares);
        return expenseRepository.save(expense); // Cascade saves shares too
    }

    public List<Expense> getAllExpensesByGroup(Long groupId) {
        return expenseRepository.findAll().stream()
                .filter(e -> e.getGroup().getId().equals(groupId))
                .collect(Collectors.toList());
    }

}
