package com.bhanu.splitwise.expense;

import com.bhanu.splitwise.User;
import com.bhanu.splitwise.split.PercentSplit;
import com.bhanu.splitwise.split.Split;

import java.util.List;

public class PercentExpense extends Expense {

    public PercentExpense(double amount, User paidBy, List<Split> splits, ExpenseMetaData expenseMetaData) {
        super(amount, paidBy, splits, expenseMetaData);
    }

    @Override
    public boolean validate() {
        for (Split split: getSplits()){
            if (!(split instanceof PercentSplit)){
                return false;
            }
        }
        double totalPercent = 100;
        double sumSplitPercent = 0;
        for (Split split: getSplits()){
            PercentSplit percentSplit = (PercentSplit) split;
            sumSplitPercent+=percentSplit.getPercent();
        }
        if (totalPercent != sumSplitPercent){
            return false;
        }
        return true;
    }
}
