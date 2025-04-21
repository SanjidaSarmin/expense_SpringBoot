package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private Double totalAmount;

    @ManyToOne
    private Groups group;

    @ManyToOne
    private Member paidBy;

    private LocalDate date;

    @OneToMany(mappedBy = "expense", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ExpenseShare> shares;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Groups getGroup() {
        return group;
    }

    public void setGroup(Groups group) {
        this.group = group;
    }

    public Member getPaidBy() {
        return paidBy;
    }

    public void setPaidBy(Member paidBy) {
        this.paidBy = paidBy;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<ExpenseShare> getShares() {
        return shares;
    }

    public void setShares(List<ExpenseShare> shares) {
        this.shares = shares;
    }
}
