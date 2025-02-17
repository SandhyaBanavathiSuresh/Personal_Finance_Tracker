package com.expense.tracker.repository;

import com.expense.tracker.entity.Expenses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExpenseRepo extends JpaRepository<Expenses, Long> {

    List<Expenses> findByDateBetween(LocalDate startDate, LocalDate endDate);
}
