package com.bhanu.splitwise.expense;

import com.bhanu.splitwise.User;
import com.bhanu.splitwise.split.Split;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpenseManager {
    List<Expense> expenses;
    Map<String, User> userMap;
    Map<String, Map<String,Double>> balanceSheet;

    public ExpenseManager() {
        expenses = new ArrayList<>();
        userMap = new HashMap<>();
        balanceSheet = new HashMap<>();
    }

    public void addUser(User user){
        userMap.put(user.getId(), user);
        balanceSheet.put(user.getId(), new HashMap<>());
    }

    public void addExpense(ExpenseType expenseType, double amount, String paidBy, List<Split> splits, ExpenseMetaData expenseMetaData){
        Expense expense = ExpenseService.createExpense(expenseType, amount, userMap.get(paidBy), splits, expenseMetaData);
        expenses.add(expense);

        for (Split split: expense.getSplits()){
            String paidTo = split.getUser().getId();
            Map<String, Double> balances = balanceSheet.get(paidBy);
            if (!balances.containsKey(paidTo)){
                balances.put(paidTo, 0.0);
            }
            balances.put(paidTo, balances.get(paidTo) + split.getAmount());

            balances = balanceSheet.get(paidTo);
            if (!balances.containsKey(paidBy)){
                balances.put(paidBy,0.0);
            }
            balances.put(paidBy, balances.get(paidBy) - split.getAmount());
        }
    }
    public void showBalance(String userId){
        boolean isEmpty = true;
        for (Map.Entry<String, Double> userBalance: balanceSheet.get(userId).entrySet()){
            if (userBalance.getValue() != 0){
                isEmpty = false;
                System.out.println("userId:" + userBalance.getKey()+" balance:"+userBalance.getValue());
            }
        }
        if (isEmpty){
            System.out.println("No Balance found");
        }
    }
}
