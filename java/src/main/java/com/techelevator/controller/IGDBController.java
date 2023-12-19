package com.techelevator.controller;

import com.techelevator.model.IGDBModels.*;
import com.techelevator.model.TEVGDBModels.TEVGDBGame;
import com.techelevator.model.TEVGDBModels.TEVGDBSearchGame;
import com.techelevator.services.IGDBAuthenticationService;
import com.techelevator.services.IGDBService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class IGDBController {

    private IGDBAuthenticationService authService = new IGDBAuthenticationService();
    private final IGDBAuthToken token = authService.getToken();
    private final IGDBService igdbService = new IGDBService(token);

    public IGDBController() {
    }

    public TEVGDBGame getGameById(int id) {
        IGDBGame igdbGame = igdbService.getGameById(id);
        if (igdbGame == null || isAgeRestricted(igdbGame)) {
            return null;
        }
        return new TEVGDBGame(igdbGame);
    }

    public List<TEVGDBSearchGame> getGamesByTitleSearch(String searchTitle) {
        IGDBGame[] igdbGames = igdbService.getGamesByTitleSearch(searchTitle);
        if (igdbGames == null) {
            return null;
        }
        List<TEVGDBSearchGame> TEGames = new ArrayList<>();
        for (IGDBGame igdbGame : igdbGames) {
            if (igdbGame.getAge_ratings() != null && igdbGame.getRelease_dates() != null) {
                if (!isAgeRestricted(igdbGame)) {
                    TEVGDBSearchGame game = new TEVGDBSearchGame(igdbGame);
                    TEGames.add(game);
                }
            }
        }
        return TEGames;
    }

    public TEVGDBGame getRandomGame() {
        IGDBGame igdbGame = null;
        Random rand = new Random();
        int totalGameCount = igdbService.getTotalGameCount();
        while (true) {
            int offset = totalGameCount - rand.nextInt(totalGameCount) - 200;
            IGDBGame[] igdbGames = igdbService.getRandomGames(offset);
            if (igdbGames != null) {
                for (IGDBGame game : igdbGames) {
                    if (game.getAge_ratings() != null && game.getRelease_dates() != null) {
                        if (!isAgeRestricted(game)) {
                            return new TEVGDBGame(game);
                        }
                    }
                }
            }
        }
    }

    private boolean isAgeRestricted(IGDBGame game) {
        boolean isAgeRestricted = false;
        if (game.getESRBRating() <= 0 || game.getESRBRating() > 9 || game.getOurReleaseDate() == 0) {
            isAgeRestricted = true;
        }
        return isAgeRestricted;
    }

}
