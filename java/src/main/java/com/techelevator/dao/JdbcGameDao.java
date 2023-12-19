package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.IGDBModels.*;
import com.techelevator.model.TEVGDBModels.TEVGDBCompany;
import com.techelevator.model.TEVGDBModels.TEVGDBGame;
import com.techelevator.model.TEVGDBModels.TEVGDBInvolvedCompany;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcGameDao implements GameDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcGameDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public TEVGDBGame getGameById(int gameId) {
        TEVGDBGame game = null;

        String getGameSql = "SELECT id, game_name, esrb_rating, release_date, cover_url, summary FROM games WHERE id = ?;";
        String getPlatformsSql = "SELECT gp.platform_id, p.platform_name FROM games_platforms gp JOIN platforms p ON gp.platform_id = p.id WHERE gp.game_id = ?;";
        String getCompanySql = "SELECT gc.company_id, c.logo, c.company_name, c.company_description, gc.is_developer, gc.is_publisher " +
                "FROM games_company gc JOIN company c ON gc.company_id = c.id WHERE gc.game_id = ? ORDER BY gc.is_publisher DESC, gc.is_developer DESC, c.id ASC;";
        String getWebsiteSql = "SELECT gw.website_id, w.website_url, w.website_type FROM games_website gw JOIN website w ON gw.website_id = w.id " +
                "WHERE gw.game_id = ? ORDER BY w.website_type ASC;";
        String getGenreSql = "SELECT gg.genre_id, g.genre_name FROM games_genre gg JOIN genre g ON gg.genre_id = g.id WHERE gg.game_id = ?;";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(getGameSql, gameId);
            if (results.next()) {
                game = mapRowToGame(results);
            }

            if (game != null) {
                List<IGDBPlatform> platforms = new ArrayList<>();
                results = jdbcTemplate.queryForRowSet(getPlatformsSql, gameId);
                while (results.next()) {
                    platforms.add(mapRowsToPlatforms(results));
                }
                game.setPlatforms(platforms);

                List<TEVGDBInvolvedCompany> companies = new ArrayList<>();
                results = jdbcTemplate.queryForRowSet(getCompanySql, gameId);
                while (results.next()) {
                    companies.add(mapRowsToCompanies(results));
                }
                game.setInvolved_companies(companies);

                List<IGDBWebsite> websites = new ArrayList<>();
                results = jdbcTemplate.queryForRowSet(getWebsiteSql, gameId);
                while (results.next()) {
                    websites.add(mapRowsToWebsites(results));
                }
                game.setWebsites(websites);

                List<IGDBGenre> genres = new ArrayList<>();
                results = jdbcTemplate.queryForRowSet(getGenreSql, gameId);
                while (results.next()) {
                    genres.add(mapRowsToGenres(results));
                }
                game.setGenres(genres);
            }

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return game;
    }

    @Override
    public TEVGDBGame createGame(TEVGDBGame game) {
        TEVGDBGame newGame = null;

        String createGameSql = "INSERT INTO games (id, game_name, esrb_rating, release_date, cover_url, summary) VALUES (?, ?, ?, ?, ?, ?) RETURNING id;";

        String createCompanySql = "INSERT INTO company (id, logo, company_name, company_description) VALUES (?, ?, ?, ?) RETURNING id;";
        String createGamesCompanySql = "INSERT INTO games_company (game_id, company_id, is_developer, is_publisher) VALUES (?, ?, ?, ?) RETURNING game_id;";

        String createPlatformsSql = "INSERT INTO platforms (id, platform_name) VALUES (?, ?) RETURNING id;";
        String createGamesPlatformsSql = "INSERT INTO games_platforms (game_id, platform_id) VALUES (?, ?) RETURNING game_id;";

        String createGenreSql = "INSERT INTO genre (id, genre_name) VALUES (?, ?) RETURNING id;";
        String createGamesGenreSql = "INSERT INTO games_genre (game_id, genre_id) VALUES (?, ?) RETURNING game_id;";

        String createWebsiteSql = "INSERT INTO website (id, website_url, website_type) VALUES (?, ?, ?) RETURNING id;";
        String createGamesWebsiteSql = "INSERT INTO games_website (game_id, website_id) VALUES (?, ?) RETURNING game_id;";


        try {
            int newGameId = jdbcTemplate.queryForObject(createGameSql, int.class, game.getId(), game.getName(), game.getEsrbRating(), game.getReleaseDate(), game.getCoverURL(), game.getSummary());

            List<TEVGDBInvolvedCompany> companies = game.getInvolved_companies();
            List<IGDBPlatform> platforms = game.getPlatforms();
            List<IGDBGenre> genres = game.getGenres();
            List<IGDBWebsite> websites = game.getWebsites();

            if (companies != null) {
                for (TEVGDBInvolvedCompany invCompany : companies) {
                    TEVGDBCompany company = invCompany.getCompany();
                    if (!isCompanyInDatabase(company)) {
                        jdbcTemplate.queryForObject(createCompanySql, int.class, company.getId(), company.getLogo(), company.getName(), company.getDescription());
                    }
                    jdbcTemplate.queryForObject(createGamesCompanySql, int.class, newGameId, company.getId(), invCompany.isDeveloper(), invCompany.isPublisher());
                }
            }

            if (platforms != null) {
                for (IGDBPlatform platform : platforms) {
                    if (!isPlatformInDatabase(platform)) {
                        jdbcTemplate.queryForObject(createPlatformsSql, int.class, platform.getId(), platform.getName());
                    }
                    jdbcTemplate.queryForObject(createGamesPlatformsSql, int.class, newGameId, platform.getId());
                }
            }

            if (genres != null) {
                for (IGDBGenre genre : genres) {
                    if (!isGenreInDatabase(genre)) {
                        jdbcTemplate.queryForObject(createGenreSql, int.class, genre.getId(), genre.getName());
                    }
                    jdbcTemplate.queryForObject(createGamesGenreSql, int.class, newGameId, genre.getId());
                }
            }

            if (websites != null) {
                for (IGDBWebsite website : websites) {
                    if (!isWebsiteInDatabase(website)) {
                        jdbcTemplate.queryForObject(createWebsiteSql, int.class, website.getId(), website.getUrl(), website.getCategory());
                    }
                    jdbcTemplate.queryForObject(createGamesWebsiteSql, int.class, newGameId, website.getId());
                }
            }

            newGame = getGameById(newGameId);

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return newGame;
    }

    @Override
    public TEVGDBGame updateGame(TEVGDBGame game) {
        return game; //todo
    }

    @Override
    public boolean deleteGameById(int gameId) {

        int numberOfRows = 0;

        String deleteGamesCompanySql = "DELETE FROM games_company WHERE game_id = ?;";
        String deleteGamesGenreSql = "DELETE FROM games_genre WHERE game_id = ?;";
        String deleteGamesWebsiteSql = "DELETE FROM games_website WHERE game_id = ?;";
        String deleteGamesPlatformsSql = "DELETE FROM games_platforms WHERE game_id = ?;";
        String deleteUserListsSql = "DELETE FROM user_lists_games WHERE game_id = ?;";
        String deleteAssociatedGameCommentsSql = "DELETE FROM user_comments WHERE review_id IN (SELECT id FROM user_reviews WHERE game_id = ?);";
        String deleteUserReviewsSql = "DELETE FROM user_reviews WHERE game_id = ?;";

        String deleteGameSql = "DELETE FROM games WHERE id = ?;";

        try {
            // delete any foreign key references to game_id
            jdbcTemplate.update(deleteGamesCompanySql, gameId);
            jdbcTemplate.update(deleteGamesGenreSql, gameId);
            jdbcTemplate.update(deleteGamesWebsiteSql, gameId);
            jdbcTemplate.update(deleteGamesPlatformsSql, gameId);
            jdbcTemplate.update(deleteUserListsSql, gameId);
            jdbcTemplate.update(deleteAssociatedGameCommentsSql, gameId);
            jdbcTemplate.update(deleteUserReviewsSql, gameId);

            // now safe to delete the game
            numberOfRows = jdbcTemplate.update(deleteGameSql, gameId);

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return (numberOfRows > 0);
    }

    private boolean isCompanyInDatabase(TEVGDBCompany company) {
        String sql = "SELECT id FROM company WHERE id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, company.getId());
        return results.next();
    }

    private boolean isPlatformInDatabase(IGDBPlatform platform) {
        String sql = "SELECT id FROM platforms WHERE id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, platform.getId());
        return results.next();
    }

    private boolean isGenreInDatabase(IGDBGenre genre) {
        String sql = "SELECT id FROM genre WHERE id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, genre.getId());
        return results.next();
    }

    private boolean isWebsiteInDatabase(IGDBWebsite website) {
        String sql = "SELECT id FROM website WHERE id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, website.getId());
        return results.next();
    }

    private TEVGDBGame mapRowToGame(SqlRowSet rs) {
        TEVGDBGame game = new TEVGDBGame();
        game.setId(rs.getInt("id"));
        game.setName(rs.getString("game_name"));
        game.setEsrbRating(rs.getInt("esrb_rating"));
        game.setReleaseDate(rs.getInt("release_date"));
        game.setCoverURL(rs.getString("cover_url"));
        game.setSummary(rs.getString("summary"));
        return game;
    }

    private IGDBPlatform mapRowsToPlatforms(SqlRowSet rs) {
        IGDBPlatform platform = new IGDBPlatform();
        platform.setId(rs.getInt("platform_id"));
        platform.setName(rs.getString("platform_name"));
        return platform;
    }

    private TEVGDBInvolvedCompany mapRowsToCompanies(SqlRowSet rs) {
        TEVGDBInvolvedCompany involvedCompany = new TEVGDBInvolvedCompany();
        TEVGDBCompany company = new TEVGDBCompany();
        company.setId(rs.getInt("company_id"));
        company.setLogo(rs.getString("logo"));
        company.setName(rs.getString("company_name"));
        company.setDescription(rs.getString("company_description"));

        involvedCompany.setCompany(company);
        involvedCompany.setDeveloper(rs.getBoolean("is_developer"));
        involvedCompany.setPublisher(rs.getBoolean("is_publisher"));
        return involvedCompany;
    }

    private IGDBWebsite mapRowsToWebsites(SqlRowSet rs) {
        IGDBWebsite website = new IGDBWebsite();
        website.setId(rs.getInt("website_id"));
        website.setUrl(rs.getString("website_url"));
        website.setCategory(rs.getInt("website_type"));
        return website;
    }

    private IGDBGenre mapRowsToGenres(SqlRowSet rs) {
        IGDBGenre genre = new IGDBGenre();
        genre.setId(rs.getInt("genre_id"));
        genre.setName(rs.getString("genre_name"));
        return genre;
    }

}
