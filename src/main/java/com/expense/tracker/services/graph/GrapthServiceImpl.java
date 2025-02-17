package com.expense.tracker.services.graph;

import com.expense.tracker.dto.GraphDTO;
import com.expense.tracker.repository.ExpenseRepo;
import com.expense.tracker.repository.IncomeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

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
}
