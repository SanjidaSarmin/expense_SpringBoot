package com.example.demo.dto;

public class ExpenseSplitRequest {
    private Long memberId;
    private Double amountOwed;

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Double getAmountOwed() {
        return amountOwed;
    }

    public void setAmountOwed(Double amountOwed) {
        this.amountOwed = amountOwed;
    }
}
