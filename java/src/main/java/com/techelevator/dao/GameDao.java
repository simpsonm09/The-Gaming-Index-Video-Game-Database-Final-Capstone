package com.techelevator.dao;

import com.techelevator.model.TEVGDBModels.TEVGDBGame;

public interface GameDao {
    TEVGDBGame getGameById(int id);

    TEVGDBGame createGame(TEVGDBGame game);

    TEVGDBGame updateGame(TEVGDBGame game);

    boolean deleteGameById(int id);

}
