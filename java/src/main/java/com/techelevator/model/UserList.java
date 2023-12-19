package com.techelevator.model;

import com.techelevator.model.TEVGDBModels.TEVGDBGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserList {

    private int id;
    private int listType;
    private String customType;
    private int userId;
    private List<TEVGDBGame> games = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getListType() {
        return listType;
    }

    public void setListType(int listType) {
        this.listType = listType;
    }

    public String getCustomType() {
        return customType;
    }

    public void setCustomType(String customType) {
        this.customType = customType;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<TEVGDBGame> getGames() {
        return games;
    }

    public void setGames(List<TEVGDBGame> games) {
        this.games = games;
    }

    @Override
    public String toString() {
        return "UserList{" +
                "id=" + id +
                ", listType=" + listType +
                ", customType='" + customType + '\'' +
                ", userId=" + userId +
                ", games=" + games +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserList userList = (UserList) o;
        return id == userList.id && listType == userList.listType && userId == userList.userId && Objects.equals(customType, userList.customType) && Objects.equals(games, userList.games);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, listType, customType, userId, games);
    }
}
