package com.example.cnExpense.DAL;

import com.example.cnExpense.entities.*;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import java.util.List;

@Repository
public class UserDalImpl implements UserDal {

    @Autowired
    EntityManager entityManager;

    @Override
    public User getUserById(Integer id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery(
                "SELECT u FROM User u", User.class).getResultList();
    }

    @Override
    public User saveUser(User user) {
        if (checkUserExist(user)) {
            throw new RuntimeException("User already exists!");
        }
        entityManager.persist(user);
        return user;
    }


    @Override
    public boolean checkUserExist(User user){
        Long count = (Long) entityManager.createQuery("SELECT COUNT(u) FROM User u WHERE u.username = :username")
                .setParameter("username", user.getUsername())
                .getSingleResult();

        return count > 0;
    }

    @Override
    public User findUser(User newUser) {
        return entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
                .setParameter("username", newUser.getUsername())
                .getResultStream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<User> filteredUserListByCalendar(String day, String month, String year) {
        StringBuilder query = new StringBuilder("SELECT u FROM User u JOIN u.incomes i WHERE 1=1");

        if (day != null && !day.isEmpty()) {
            query.append(" AND FUNCTION('DAY', i.date) = :day");
        }
        if (month != null && !month.isEmpty()) {
            query.append(" AND FUNCTION('MONTH', i.date) = :month");
        }
        if (year != null && !year.isEmpty()) {
            query.append(" AND FUNCTION('YEAR', i.date) = :year");
        }

        // Create the query and set parameters
        var typedQuery = entityManager.createQuery(query.toString(), User.class);

        if (day != null && !day.isEmpty()) {
            typedQuery.setParameter("day", Integer.parseInt(day));
        }
        if (month != null && !month.isEmpty()) {
            typedQuery.setParameter("month", Integer.parseInt(month));
        }
        if (year != null && !year.isEmpty()) {
            typedQuery.setParameter("year", Integer.parseInt(year));
        }

        // Execute the query and return filtered list of users
        return typedQuery.getResultList();
    }

    @Override
    public List<User> filterUserListByType(String incomeType, String expenseType) {
        StringBuilder query = new StringBuilder("SELECT u FROM User u JOIN u.incomes i JOIN i.incomeTypes it WHERE 1=1");

        if (incomeType != null && !incomeType.isEmpty()) {
            query.append(" AND LOWER(it.name) = :incomeType");
        }
        if (expenseType != null && !expenseType.isEmpty()) {
            query.append(" AND LOWER(i.expense.expenseTypes.name) = :expenseType");
        }

        // Create the query and set parameters
        var typedQuery = entityManager.createQuery(query.toString(), User.class);

        if (incomeType != null && !incomeType.isEmpty()) {
            typedQuery.setParameter("incomeType", incomeType.toLowerCase());
        }
        if (expenseType != null && !expenseType.isEmpty()) {
            typedQuery.setParameter("expenseType", expenseType.toLowerCase());
        }

        // Execute the query and return filtered list of users
        return typedQuery.getResultList();
    }

}


