package com.expense.tracker.dto;

import com.expense.tracker.entity.Expenses;
import com.expense.tracker.entity.Income;
import lombok.Data;

@Data
public class StatisticDTO {

    //To get the Total income and Expense
    private Double income;
    private Double expense;

    //To get the Latest income and Expense
    private Income latestIncome;
    private Expenses latestExpense;

    //To get min, max and balance of income and expense
    private Double balance;

    private Double minIncome;
    private Double maxIncome;

    private Double minExpense;
    private Double maxExpense;

}
