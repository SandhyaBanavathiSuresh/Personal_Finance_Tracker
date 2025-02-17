package com.expense.tracker.services.income;

import com.expense.tracker.dto.IncomeDTO;
import com.expense.tracker.entity.Income;
import com.expense.tracker.repository.IncomeRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IncomeServiceImpl implements IncomeService{

    private final IncomeRepo incomeRepo;

    public Income saveOrUpdateIncome(Income income, IncomeDTO incomeDTO){
        income.setTitle(incomeDTO.getTitle());
        income.setDate(incomeDTO.getDate());
        income.setAmount(incomeDTO.getAmount());
        income.setCategory(incomeDTO.getCategory());
        income.setDescription(incomeDTO.getDescription());

        return incomeRepo.save(income);
    }

    public Income postIncome(IncomeDTO incomeDTO){
        return saveOrUpdateIncome(new Income(), incomeDTO);
    }

    public List<IncomeDTO> getAllIncome(){
        return incomeRepo.findAll().stream()
                .sorted(Comparator.comparing(Income::getDate).reversed())
                .map(Income::getIncomeDTO)
                .collect(Collectors.toList());
    }

    public IncomeDTO getIncomeById(Long id){
        Optional<Income> income = incomeRepo.findById(id);
        if(income.isPresent()){
            return income.get().getIncomeDTO();
        }else{
            throw new EntityNotFoundException("Income with id: " + id + "not found");
        }
    }

    public Income updateIncome(Long id, IncomeDTO incomeDTO){
        Optional<Income> income = incomeRepo.findById(id);
        if(income.isPresent()){
            return saveOrUpdateIncome(income.get(), incomeDTO);
        }else{
            throw new EntityNotFoundException("Income with id: " + id + "not found");
        }
    }

    public void deleteIncome(Long id){
        Optional<Income> income = incomeRepo.findById(id);
        if(income.isPresent()){
            incomeRepo.deleteById(id);
        }else{
            throw new EntityNotFoundException("Income with id: " + id + "not found");
        }
    }

}
