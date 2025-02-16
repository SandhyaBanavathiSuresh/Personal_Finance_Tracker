package com.expense.tracker.dto;

import lombok.Data;

@Data
public class ExpenseDTO {

    private Long id;
    private String title;
    private String description;
    private String category;
    private String data;
    private Integer amount;
}
