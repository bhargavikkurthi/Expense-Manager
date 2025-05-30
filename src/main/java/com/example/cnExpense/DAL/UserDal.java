package com.example.cnExpense.DAL;

import com.example.cnExpense.entities.User;

import java.util.List;

public interface UserDal {
    List<User> getAllUsers();

    boolean checkUserExist(User user);

    public User getUserById(Integer id);

    public User findUser(User newUser);

    public User saveUser(User user);

    List<User> filteredUserListByCalendar(String day, String month, String year);

    List<User> filterUserListByType(String incomeType, String expenseType);
}