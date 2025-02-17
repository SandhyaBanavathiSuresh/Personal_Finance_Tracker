package com.expense.tracker.services.graph;

import com.expense.tracker.dto.GraphDTO;
import com.expense.tracker.dto.StatisticDTO;
import com.expense.tracker.entity.Expenses;
import com.expense.tracker.entity.Income;
import com.expense.tracker.repository.ExpenseRepo;
import com.expense.tracker.repository.IncomeRepo;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

@Service
@RequiredArgsConstructor
public class GrapthServiceImpl implements GraphService {

    private final ExpenseRepo expenseRepo;
    private final IncomeRepo incomeRepo;

    public GraphDTO getGraphData(){
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusDays(27);

        GraphDTO graphDTO = new GraphDTO();
        graphDTO.setExpenseList(expenseRepo.findByDateBetween(startDate, endDate));
        graphDTO.setIncomeList(incomeRepo.findByDateBetween(startDate, endDate));

        return graphDTO;
    }

    public StatisticDTO getStatisticData(){
        Double totalIncome = incomeRepo.totalIncome();
        Double totalExpense = expenseRepo.totalExpense();

        Optional<Income> latestIncome = incomeRepo.findAllByOrderByDateDesc();
        Optional<Expenses> latestExpense = expenseRepo.findAllByOrderByDateDesc();

        StatisticDTO statisticDTO = new StatisticDTO();
        statisticDTO.setIncome(totalIncome);
        statisticDTO.setExpense(totalExpense);
        statisticDTO.setBalance(totalIncome - totalExpense);

        latestIncome.ifPresent(statisticDTO::setLatestIncome);
        latestExpense.ifPresent(statisticDTO::setLatestExpense);

        //To find and set min and max Income, Expense
        List<Income> incomeList = incomeRepo.findAll();
        List<Expenses> expenseList = expenseRepo.findAll();

        OptionalDouble minIncome = incomeList.stream().mapToDouble(Income::getAmount).min();
        OptionalDouble maxIncome = incomeList.stream().mapToDouble(Income::getAmount).max();

        OptionalDouble minExpense = expenseList.stream().mapToDouble(Expenses::getAmount).min();
        OptionalDouble maxExpense = expenseList.stream().mapToDouble(Expenses::getAmount).max();

        statisticDTO.setMinIncome(minIncome.isPresent() ? minIncome.getAsDouble() : null);
        statisticDTO.setMaxIncome(maxIncome.isPresent() ? maxIncome.getAsDouble() : null);

        statisticDTO.setMinExpense(minExpense.isPresent() ? minExpense.getAsDouble() : null);
        statisticDTO.setMaxExpense(maxExpense.isPresent() ? maxExpense.getAsDouble() : null);

        return statisticDTO;
    }

}
