package com.expense.tracker.services.expense;

import com.expense.tracker.dto.ExpenseDTO;
import com.expense.tracker.entity.Expenses;

import java.util.List;

public interface ExpenseService {

    Expenses postExpense(ExpenseDTO expenseDTO);

    List<Expenses> getAllExpenses();

    Expenses getExpenseById(Long id);
}
