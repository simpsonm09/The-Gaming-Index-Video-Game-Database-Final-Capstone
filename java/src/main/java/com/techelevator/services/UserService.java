package com.techelevator.services;

import com.techelevator.dao.UserDao;
import com.techelevator.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserService {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User getUserById(int userId) {
        return userDao.getUserById(userId);
    }

    public boolean deleteUserById(int userId) {
        return userDao.deleteUserById(userId);
    }

}
