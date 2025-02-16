package com.expense.tracker.services.expense;

import com.expense.tracker.dto.ExpenseDTO;
import com.expense.tracker.entity.Expenses;

public interface ExpenseService {

    Expenses postExpense(ExpenseDTO expenseDTO);
}
