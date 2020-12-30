package com.bhanu.splitwise.expense;

import com.bhanu.splitwise.User;
import com.bhanu.splitwise.split.ExactSplit;
import com.bhanu.splitwise.split.Split;

import java.util.List;

public class ExactExpense extends Expense{

    public ExactExpense(double amount, User paidBy, List<Split> splits, ExpenseMetaData expenseMetaData) {
        super(amount, paidBy, splits, expenseMetaData);
    }

    @Override
    public boolean validate() {
        for (Split split: getSplits()){
            if (!(split instanceof ExactSplit)){
                return false;
            }
        }
        double totalAmount = getAmount();
        double sumSplitAmount = 0;
        for (Split split: getSplits()){
            ExactSplit exactSplit = (ExactSplit) split;
            sumSplitAmount+=exactSplit.getAmount();
        }
        if (sumSplitAmount != totalAmount) {
            return false;
        }
        return true;
    }
}
