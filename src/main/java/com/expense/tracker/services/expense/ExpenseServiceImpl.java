package com.expense.tracker.services.expense;

import com.expense.tracker.dto.ExpenseDTO;
import com.expense.tracker.entity.Expenses;
import com.expense.tracker.repository.ExpenseRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<Expenses> getAllExpenses(){
        return expenseRepo.findAll().stream().sorted(Comparator.comparing(Expenses::getDate).reversed()).collect(Collectors.toList());
    }

    public Expenses getExpenseById(Long id){
        Optional<Expenses> expense = expenseRepo.findById(id);
        if(expense.isPresent()){
            return expense.get();
        }else{
            throw new EntityNotFoundException("No Expense was found for the given ID: " + id);
        }
    }

    public Expenses updateExpense(Long id, ExpenseDTO expenseDTO){
        Optional<Expenses> expense = expenseRepo.findById(id);
        if(expense.isPresent()){
            return saveOrUpdateExpense(expense.get(), expenseDTO);
        }else{
            throw new EntityNotFoundException("Could not find the expense");
        }
    }

    public void deleteExpense(Long id){
        Optional<Expenses> expense = expenseRepo.findById(id);
        if(expense.isPresent()){
            expenseRepo.deleteById(id);
        }else{
            throw new EntityNotFoundException("Could not find the expense");
        }
    }
}
