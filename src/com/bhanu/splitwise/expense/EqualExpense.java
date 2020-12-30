package com.bhanu.splitwise.expense;

import com.bhanu.splitwise.User;
import com.bhanu.splitwise.split.EqualSplit;
import com.bhanu.splitwise.split.Split;

import java.util.List;

public class EqualExpense extends Expense{

    public EqualExpense(double amount, User paidBy, List<Split> splits, ExpenseMetaData expenseMetaData) {
        super(amount, paidBy, splits, expenseMetaData);
    }

    @Override
    public boolean validate() {
        for (Split split: getSplits()){
            if (!(split instanceof EqualSplit)){
                return false;
            }
        }
        return true;
    }
}
