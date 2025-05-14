package com.example.cnExpense.service;

import com.example.cnExpense.DAL.UserDal;
import com.example.cnExpense.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDal userDal;

    @Transactional
    public User getUserById(Integer id){
        return userDal.getUserById(id);
    }

    @Transactional
    public List<User> getAllUsers() {
        return userDal.getAllUsers();
    }

    @Transactional
    public User saveUser(User user) {
        return userDal.saveUser(user);
    }

    @Transactional
    public boolean checkUserExist(User user) {
        return userDal.checkUserExist(user);
    }

    @Transactional
    public User findUser(User newUser){
        return userDal.findUser(newUser);
    }

    @Transactional
    public List<User> filteredUserListByCalendar(String day, String month, String year) {
        return  userDal.filteredUserListByCalendar(day, month, year);
    }

    @Transactional
    public List<User> filterUserListByType(String incomeType, String expenseType) {
        return userDal.filterUserListByType(incomeType, expenseType);
    }
}