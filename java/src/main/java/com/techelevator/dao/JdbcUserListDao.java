package com.techelevator.dao;


import com.techelevator.exception.DaoException;
import com.techelevator.model.Review;
import com.techelevator.model.TEVGDBModels.TEVGDBGame;
import com.techelevator.model.UserList;
import com.techelevator.services.TEVGDBService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcUserListDao implements UserListDao {

    private final JdbcTemplate jdbcTemplate;
    private final TEVGDBService gameService;

    public JdbcUserListDao(JdbcTemplate jdbcTemplate, TEVGDBService gameService) {
        this.jdbcTemplate = jdbcTemplate;
        this.gameService = gameService;
    }

    @Override
    public List<UserList> getUserListsByUserId(int userId) {
        List<UserList> userLists = new ArrayList<>();

        String userListsSql = "SELECT * FROM user_lists WHERE user_id = ? ORDER BY id;";
        String userListsGamesSql = "SELECT game_id FROM user_lists_games WHERE list_id = ?;";
        try {
            SqlRowSet listResults = jdbcTemplate.queryForRowSet(userListsSql, userId);
            while (listResults.next()) {
                UserList userList = mapRowToUserList(listResults);
                List<TEVGDBGame> gamesList = new ArrayList<>();
                SqlRowSet gameResults = jdbcTemplate.queryForRowSet(userListsGamesSql, userList.getId());
                while (gameResults.next()) {
                    gamesList.add(gameService.getGameById(gameResults.getInt("game_id")));
                }
                userList.setGames(gamesList);
                userLists.add(userList);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return userLists;
    }

    @Override
    public List<UserList> getUserListByGameIdUserId(int gameId, int userId) {
        List<UserList> userLists = new ArrayList<>();

        String userListsSql = "SELECT * FROM user_lists WHERE user_id = ? AND id IN (SELECT list_id FROM user_lists_games WHERE " +
                "game_id = ?) ORDER BY id;";
        String userListsGamesSql = "SELECT game_id FROM user_lists_games WHERE list_id = ?;";
        try {
            SqlRowSet listResults = jdbcTemplate.queryForRowSet(userListsSql, userId, gameId);
            while (listResults.next()) {
                UserList userList = mapRowToUserList(listResults);
                List<TEVGDBGame> gamesList = new ArrayList<>();
                SqlRowSet gameResults = jdbcTemplate.queryForRowSet(userListsGamesSql, userList.getId());
                while (gameResults.next()) {
                    gamesList.add(gameService.getGameById(gameResults.getInt("game_id")));
                }
                userList.setGames(gamesList);
                userLists.add(userList);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return userLists;
    }

    @Override
    public UserList getUserListById(int id) {
        UserList userList = new UserList();

        String userListsSql = "SELECT * FROM user_lists WHERE id = ?;";
        String userListsGamesSql = "SELECT game_id FROM user_lists_games WHERE list_id = ?;";
        try {
            SqlRowSet listResults = jdbcTemplate.queryForRowSet(userListsSql, id);
            if (listResults.next()) {
                userList = mapRowToUserList(listResults);
                List<TEVGDBGame> gamesList = new ArrayList<>();
                SqlRowSet gameResults = jdbcTemplate.queryForRowSet(userListsGamesSql, userList.getId());
                while (gameResults.next()) {
                    gamesList.add(gameService.getGameById(gameResults.getInt("game_id")));
                }
                userList.setGames(gamesList);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return userList;
    }

    @Override
    public UserList createUserList(UserList userList) {
        UserList newUserList = new UserList();

        String sql = "INSERT INTO user_lists (list_type, custom_type, user_id) VALUES (?, ?, ?) RETURNING id;";

        try {
            int newUserListId = jdbcTemplate.queryForObject(sql, int.class, userList.getListType(), userList.getCustomType(),
                    userList.getUserId());
            newUserList = getUserListById(newUserListId);
            if (newUserList.getGames().size() == 0) {
                newUserList.setGames(new ArrayList<>());
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return newUserList;
    }

    @Override
    public UserList updateUserList(UserList userList) {
        UserList updatedUserList = new UserList();

        String sql = "UPDATE user_lists SET custom_type = ? WHERE id = ? RETURNING id;";

        try {
            int updatedUserListId = jdbcTemplate.queryForObject(sql, int.class, userList.getCustomType(), userList.getId());
            updatedUserList = getUserListById(updatedUserListId);
            if (updatedUserList.getGames().size() == 0) {
                updatedUserList.setGames(new ArrayList<>());
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return updatedUserList;
    }

    @Override
    public UserList addGameToUserList(int listId, int gameId) {
        UserList updatedUserList = new UserList();

        String sql = "INSERT INTO user_lists_games (list_id, game_id) VALUES (?, ?) RETURNING list_id;";

        try {
            int updatedUserListId = jdbcTemplate.queryForObject(sql, int.class, listId, gameId);
            updatedUserList = getUserListById(updatedUserListId);
            if (updatedUserList.getGames().size() == 0) {
                updatedUserList.setGames(new ArrayList<>());
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return updatedUserList;
    }

    @Override
    public UserList removeGameFromUserList(int listId, int gameId) {
        UserList updatedUserList = new UserList();

        String sql = "DELETE FROM user_lists_games WHERE (list_id = ? AND game_id = ?);";

        try {
            jdbcTemplate.update(sql, listId, gameId);
            updatedUserList = getUserListById(listId);
            if (updatedUserList.getGames().size() == 0) {
                updatedUserList.setGames(new ArrayList<>());
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return updatedUserList;
    }

    @Override
    public boolean deleteUserListById(int id) {
        int numberOfRows = 0;

        String deleteUserListSql = "DELETE FROM user_lists WHERE id = ?;";
        String deleteUserListGamesSql = "DELETE FROM user_lists_games WHERE list_id = ?";

        try {

            jdbcTemplate.update(deleteUserListGamesSql, id);
            numberOfRows = jdbcTemplate.update(deleteUserListSql, id);


        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return (numberOfRows > 0);
    }

    private UserList mapRowToUserList(SqlRowSet rs) {
        UserList userList = new UserList();
        userList.setId(rs.getInt("id"));
        userList.setListType(rs.getInt("list_type"));
        userList.setCustomType(rs.getString("custom_type"));
        userList.setUserId(rs.getInt("user_id"));
        return userList;
    }

}
