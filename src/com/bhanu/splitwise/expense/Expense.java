package com.bhanu.splitwise.expense;

import com.bhanu.splitwise.User;
import com.bhanu.splitwise.split.Split;

import java.util.List;

public abstract class Expense {
    private String id;
    private double amount;
    private User paidBy;
    private List<Split> splits;
    private ExpenseMetaData expenseMetaData;

    public Expense(double amount, User paidBy, List<Split> splits, ExpenseMetaData expenseMetaData) {
        this.amount = amount;
        this.paidBy = paidBy;
        this.splits = splits;
        this.expenseMetaData = expenseMetaData;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public User getPaidBy() {
        return paidBy;
    }

    public void setPaidBy(User paidBy) {
        this.paidBy = paidBy;
    }

    public List<Split> getSplits() {
        return splits;
    }

    public void setSplits(List<Split> splits) {
        this.splits = splits;
    }

    public ExpenseMetaData getExpenseMetaData() {
        return expenseMetaData;
    }

    public void setExpenseMetaData(ExpenseMetaData expenseMetaData) {
        this.expenseMetaData = expenseMetaData;
    }

    public abstract boolean validate();
}
