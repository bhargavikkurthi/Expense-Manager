package com.example.cnExpense.DAL;

import com.example.cnExpense.entities.Expense;
import com.example.cnExpense.entities.ExpenseType;
import com.example.cnExpense.entities.Income;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;

@Repository
public class ExpenseDalImpl implements ExpenseDal {

    @Autowired
    EntityManager entityManager;

    @Override
    public Income saveExpense(Income income, Expense newExpense) {

        // Fetch Income from DB
        Income existingIncome =
                entityManager.find(Income.class, income.getId());

        if (existingIncome == null) {
            throw new IllegalArgumentException("Income not found with ID: " + income.getId());
        }

        // Persist Expense
        entityManager.persist(newExpense);
        entityManager.flush(); // ensure ID is available

        // Link both sides
        existingIncome.setExpense(newExpense);
        newExpense.setIncome(existingIncome);

        // Save ExpenseTypes if present
        if (newExpense.getExpenseTypes() != null) {
            for (ExpenseType expenseType : newExpense.getExpenseTypes()) {
                expenseType.setExpense(newExpense);
                entityManager.persist(expenseType);
            }
        }

        // Optionally persist changes (flush not needed unless immediate)
        return existingIncome;
    }
}


