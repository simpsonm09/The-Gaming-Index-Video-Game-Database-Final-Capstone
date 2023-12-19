package com.techelevator.services;

import com.techelevator.dao.GameDao;
import com.techelevator.model.TEVGDBModels.TEVGDBGame;
import org.springframework.stereotype.Component;

@Component
public class TEVGDBService {

    private final GameDao gameDao;

    public TEVGDBService(GameDao gameDao) {
        this.gameDao = gameDao;
    }

    public TEVGDBGame getGameById(int gameId) {
        return gameDao.getGameById(gameId);
    }

    public TEVGDBGame createGame(TEVGDBGame game) {
        return gameDao.createGame(game);
    }

    public TEVGDBGame updateGame(TEVGDBGame game) {
        return gameDao.updateGame(game);
    }

    public boolean deleteGameById(int gameId) {
        return gameDao.deleteGameById(gameId);
    }

}
