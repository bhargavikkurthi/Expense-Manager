package com.example.cnExpense.DAL;

import com.example.cnExpense.entities.Income;
import com.example.cnExpense.entities.IncomeType;
import com.example.cnExpense.entities.User;
import com.example.cnExpense.service.UserService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;

@Repository
public class IncomeDalImpl implements IncomeDal {

    @Autowired
    EntityManager entityManager;

    @Autowired
    UserService userService;

    @Override
    public Income getIncomeById(Integer id){
        return entityManager.find(Income.class, id);
    }


    @Override
    public Income saveIncome(User user,Income newIncome) {
        // Fetch the full User entity
        User existingUser = userService.getUserById(user.getId());

        // Determine whether we're creating or updating
        boolean isNew =
                (newIncome.getId()==null || newIncome.getId()==0);

        // Set up relationship for IncomeType
        if (newIncome.getIncomeTypes() != null) {
            for (IncomeType incomeType : newIncome.getIncomeTypes()) {
                incomeType.setIncome(newIncome);
            }
        }

        // Manage relationship between User and Income
        newIncome.getUsers().add(existingUser);
        existingUser.getIncomes().add(newIncome);

        // Persist or merge the newIncome
        if(isNew) {
            entityManager.persist(newIncome);
        } else {
            newIncome = entityManager.merge(newIncome); // Reassign result in case of detached entity
        }

        // Merge the user to update the relationship
        entityManager.merge(existingUser);

        return newIncome;
    }
}
