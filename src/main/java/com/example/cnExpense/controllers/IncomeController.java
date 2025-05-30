package com.example.cnExpense.controllers;

import com.example.cnExpense.entities.Income;
import com.example.cnExpense.entities.User;
import com.example.cnExpense.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/incomes")
public class IncomeController {

    @Autowired
    private IncomeService incomeService;

    @GetMapping("/{incomeId}")
    public Income getIncomeById(@PathVariable Integer incomeId) {
        return incomeService.getIncomeById(incomeId);
    }

    @PostMapping("/save/{userId}")
    public Income saveIncome(@PathVariable Integer userId, @RequestBody Income income) {
        User user = new User();
        user.setId(userId);
        return incomeService.saveIncome(user, income);
    }
}
