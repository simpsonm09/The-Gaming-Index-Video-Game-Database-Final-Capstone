package com.techelevator.services;

import com.techelevator.dao.UserListDao;
import com.techelevator.model.UserList;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserListService {

    private final UserListDao userListDao;

    public UserListService(UserListDao userListDao) {
        this.userListDao = userListDao;
    }

    public List<UserList> getUserListsByUserId(int userId) {
        return userListDao.getUserListsByUserId(userId);
    }

    public List<UserList> getUserListByGameIdUserId(int gameId, int userId) {
        return userListDao.getUserListByGameIdUserId(gameId, userId);
    }

    public UserList getUserListById(int id) {
        return userListDao.getUserListById(id);
    }

    public UserList createUserList(UserList userList) {
        return userListDao.createUserList(userList);
    }

    public UserList updateUserList(UserList userList) {
        return userListDao.updateUserList(userList);
    }

    public UserList addGameToUserList(int listId, int gameId) {
        return userListDao.addGameToUserList(listId, gameId);
    }

    public UserList removeGameFromUserList(int listId, int gameId) {
        return userListDao.removeGameFromUserList(listId, gameId);
    }

    public boolean deleteUserListById(int id) {
        return userListDao.deleteUserListById(id);
    }

}
