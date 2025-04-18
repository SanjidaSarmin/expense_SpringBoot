package com.example.demo.service;

import com.example.demo.entity.Groups;
import com.example.demo.entity.Transaction;
import com.example.demo.entity.Member;
import com.example.demo.repository.GroupRepository;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private MemberRepository memberRepository;

    public void splitExpense(Long groupId, Long paidById, double amount) {
        Groups group = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found"));

        Member paidBy = memberRepository.findById(paidById)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Member> members = group.getMembers();

        int totalMembers = members.size();
        double share = amount / totalMembers;

        for (Member member : members) {
            if (!member.getId().equals(paidById)) {
                Transaction t = new Transaction();
                t.setFromUser(member);      // who owes
                t.setToUser(paidBy);        // who paid
                t.setAmount(share);
                t.setGroup(group);
                transactionRepository.save(t);
            }
        }
    }
}

