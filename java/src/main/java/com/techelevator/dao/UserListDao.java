package com.techelevator.dao;

import com.techelevator.model.UserList;

import java.util.List;

public interface UserListDao {

    List<UserList> getUserListsByUserId(int userId);

    List<UserList> getUserListByGameIdUserId(int gameId, int userId);

    UserList getUserListById(int id);

    UserList createUserList(UserList userList);

    UserList updateUserList(UserList userList);

    UserList addGameToUserList(int listId, int gameId);

    UserList removeGameFromUserList(int listId, int gameId);

    boolean deleteUserListById(int id);

}
