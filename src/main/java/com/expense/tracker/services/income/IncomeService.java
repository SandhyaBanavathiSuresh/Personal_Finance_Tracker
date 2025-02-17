package com.expense.tracker.services.income;

import com.expense.tracker.dto.IncomeDTO;
import com.expense.tracker.entity.Income;

import java.util.List;

public interface IncomeService {

    Income postIncome(IncomeDTO incomeDTO);

    List<IncomeDTO> getAllIncome();

    IncomeDTO getIncomeById(Long id);

    Income updateIncome(Long id, IncomeDTO incomeDTO);

    void deleteIncome(Long id);
}
