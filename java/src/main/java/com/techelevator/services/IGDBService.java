package com.techelevator.services;

import com.techelevator.model.IGDBModels.*;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class IGDBService {

    private final String baseUrl = "https://api.igdb.com/v4";
    private final RestTemplate restTemplate = new RestTemplate();
    private final String clientID = "fp9g0g4yqrpfwoijr9iimtenbzigld";
    private final IGDBAuthToken token;

    public IGDBService(IGDBAuthToken token) {
        this.token = token;
    }

    public IGDBGame getGameById(int id) {
        String body = "fields id, name, summary, first_release_date, age_ratings.category, age_ratings.rating, " +
                "release_dates.region, release_dates.date, cover.image_id, platforms.name, genres.name, " +
                "involved_companies.publisher, involved_companies.developer, involved_companies.company.name, " +
                "involved_companies.company.description, involved_companies.company.logo.image_id, " +
                "websites.url, websites.category;" +
                "where id = " + id + ";";
        HttpEntity<String> entity = makeEntity(body);
        IGDBGame game = null;

        try {
            game = restTemplate.exchange(baseUrl + "/games", HttpMethod.POST, entity, IGDBGame[].class).getBody()[0];
        } catch (Exception e) {
            System.out.println("get game by Id Search error." + e);
        }

        return game;
    }

    public IGDBGame[] getGamesByTitleSearch(String searchTitle) {
        String body = "fields id, name, age_ratings.category, age_ratings.rating, release_dates.region, release_dates.date, cover.image_id;" +
                "search \"" + searchTitle + "\";" +
                "limit 500;";
        HttpEntity<String> entity = makeEntity(body);
        IGDBGame[] games = null;

        try {
            games = restTemplate.exchange(baseUrl + "/games", HttpMethod.POST, entity, IGDBGame[].class).getBody();
        } catch (Exception e) {
            System.out.println("get game by title search error." + e);
        }

        return games;
    }

    public IGDBGame[] getRandomGames(int offset) {
        String body = "fields id, name, summary, age_ratings.category, age_ratings.rating, release_dates.region, release_dates.date, " +
                "cover.image_id, platforms.name, genres.name, involved_companies.publisher, " +
                "involved_companies.developer, involved_companies.company.name, involved_companies.company.description, " +
                "involved_companies.company.logo.image_id, websites.url, websites.category;" +
                "limit 200;" +
                "offset " + offset + ";";
        HttpEntity<String> entity = makeEntity(body);
        IGDBGame[] games = null;

        try {
            games = restTemplate.exchange(baseUrl + "/games", HttpMethod.POST, entity, IGDBGame[].class).getBody();
        } catch (Exception e) {
            System.out.println("get game by Id Search error." + e);
        }

        return games;
    }

    public int getTotalGameCount() {
        String body = "";
        HttpEntity<String> entity = makeEntity(body);
        IGDBGameCount count = restTemplate.exchange(baseUrl + "/games/count", HttpMethod.POST, entity, IGDBGameCount.class).getBody();
        int number = count.getCount();
        return number;
    }

    private HttpEntity<String> makeEntity(String body) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Client-ID", clientID);
        headers.add("Authorization", "Bearer " + token.getAccessToken());
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(body, headers);
    }
}
