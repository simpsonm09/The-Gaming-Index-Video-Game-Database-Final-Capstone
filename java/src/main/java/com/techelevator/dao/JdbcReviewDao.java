package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Review;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcReviewDao implements ReviewDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcReviewDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Review> getReviewsByGameId(int gameId) {
        List<Review> reviews = new ArrayList<>();

        String sql = "SELECT * FROM user_reviews WHERE game_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, gameId);
            while (results.next()) {
                Review review = mapRowToReview(results);
                reviews.add(review);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return reviews;
    }

    @Override
    public Review getReviewById(int id) {
        Review review = new Review();

        String sql = "SELECT * FROM user_reviews WHERE id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
            if (results.next()) {
                review = mapRowToReview(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return review;
    }

    @Override
    public Review createReview(Review review) {
        Review newReview = new Review();

        String sql = "INSERT INTO user_reviews (num_stars, review_text, replayability_score, diversity_score, aggro_score, game_id, user_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING id;";

        try {
            int newReviewId = jdbcTemplate.queryForObject(sql, int.class, review.getNumberOfStars(), review.getReviewText(),
                    review.getReplayabilityScore(), review.getDiversityScore(), review.getAggroScore(), review.getGameId(),
                    review.getUserId());
            newReview = getReviewById(newReviewId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return newReview;
    }

    @Override
    public Review updateReview(Review review) {
        Review updatedReview = new Review();

        String sql = "UPDATE user_reviews SET num_stars = ?, review_text = ?, replayability_score = ?, diversity_score = ?," +
                "aggro_score = ?, game_id = ?, user_id = ? WHERE id = ? RETURNING id;";

        try {
            int updatedReviewId = jdbcTemplate.queryForObject(sql, int.class, review.getNumberOfStars(), review.getReviewText(), review.getReplayabilityScore(),
                    review.getDiversityScore(), review.getAggroScore(), review.getGameId(), review.getUserId(), review.getId());
            updatedReview = getReviewById(updatedReviewId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return updatedReview;
    }

    @Override
    public boolean deleteReviewById(int id) {
        int numberOfRows = 0;

        String deleteCommentsSql = "DELETE FROM user_comments WHERE review_id = ?";
        String deleteReviewSql = "DELETE FROM user_reviews WHERE id = ?;";

        try {

            jdbcTemplate.update(deleteCommentsSql, id);
            numberOfRows = jdbcTemplate.update(deleteReviewSql, id);


        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return (numberOfRows > 0);
    }

    private Review mapRowToReview(SqlRowSet rs) {
        Review review = new Review();
        review.setId(rs.getInt("id"));
        review.setNumberOfStars(rs.getInt("num_stars"));
        review.setReviewText(rs.getString("review_text"));
        review.setReplayabilityScore(rs.getInt("replayability_score"));
        review.setDiversityScore(rs.getInt("diversity_score"));
        review.setAggroScore(rs.getInt("aggro_score"));
        review.setGameId(rs.getInt("game_id"));
        review.setUserId(rs.getInt("user_id"));
        return review;
    }
}
