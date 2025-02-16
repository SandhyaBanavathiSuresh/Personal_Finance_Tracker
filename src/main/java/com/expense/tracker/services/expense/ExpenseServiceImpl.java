package com.expense.tracker.services.expense;

import com.expense.tracker.dto.ExpenseDTO;
import com.expense.tracker.entity.Expenses;
import com.expense.tracker.repository.ExpenseRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService{

    private final ExpenseRepo expenseRepo;

    private Expenses saveOrUpdateExpense(Expenses expenses, ExpenseDTO expenseDTO){
        expenses.setTitle(expenseDTO.getTitle());
        expenses.setDate(expenseDTO.getDate());
        expenses.setCategory(expenseDTO.getCategory());
        expenses.setDescription(expenseDTO.getDescription());
        expenses.setAmount(expenseDTO.getAmount());

        return expenseRepo.save(expenses);
    }

    public Expenses postExpense(ExpenseDTO expenseDTO){
        return saveOrUpdateExpense(new Expenses(), expenseDTO);
    }
}
