package com.expense.tracker.dto;

import com.expense.tracker.entity.Expenses;
import com.expense.tracker.entity.Income;
import lombok.Data;

import java.util.List;

@Data
public class GraphDTO {

    private List<Expenses> expenseList;
    private List<Income> incomeList;

}
