package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Transaction {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Member fromUser; // who owes

    @ManyToOne
    private Member toUser;   // who is owed

    private double amount;

    @ManyToOne
    private Groups group;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Member getFromUser() {
        return fromUser;
    }

    public void setFromUser(Member fromUser) {
        this.fromUser = fromUser;
    }

    public Member getToUser() {
        return toUser;
    }

    public void setToUser(Member toUser) {
        this.toUser = toUser;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Groups getGroup() {
        return group;
    }

    public void setGroup(Groups group) {
        this.group = group;
    }
}

