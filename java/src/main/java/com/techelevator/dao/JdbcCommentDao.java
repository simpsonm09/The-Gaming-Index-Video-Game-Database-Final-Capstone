package com.techelevator.dao;


import com.techelevator.exception.DaoException;
import com.techelevator.model.Comment;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcCommentDao implements CommentDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcCommentDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Comment> getCommentsByReviewId(int reviewId) {
        List<Comment> comments = new ArrayList<>();

        String sql = "SELECT * FROM user_comments WHERE review_id = ? ORDER BY id ASC;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, reviewId);
            while (results.next()) {
                Comment comment = mapRowToComment(results);
                comments.add(comment);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return comments;
    }

    @Override
    public Comment getCommentById(int id) {
        Comment comment = new Comment();

        String sql = "SELECT * FROM user_comments WHERE id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
            if (results.next()) {
                comment = mapRowToComment(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return comment;
    }

    @Override
    public Comment createComment(Comment comment) {
        Comment newComment = new Comment();

        String sql = "INSERT INTO user_comments (comment_text, review_id, user_id) VALUES (?, ?, ?) RETURNING id;";

        try {
            int newCommentId = jdbcTemplate.queryForObject(sql, int.class, comment.getCommentText(), comment.getReviewId(), comment.getUserId());
            newComment = getCommentById(newCommentId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return newComment;
    }

    @Override
    public Comment updateComment(Comment comment) {
        Comment updatedComment = new Comment();

        String sql = "UPDATE user_comments SET comment_text = ? WHERE id = ? RETURNING id;";

        try {
            int updatedCommentId = jdbcTemplate.queryForObject(sql, int.class, comment.getCommentText(), comment.getId());
            updatedComment = getCommentById(updatedCommentId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return updatedComment;
    }

    @Override
    public boolean deleteCommentById(int id) {
        int numberOfRows = 0;

        String sql = "DELETE FROM user_comments WHERE id = ?;";

        try {

            numberOfRows = jdbcTemplate.update(sql, id);

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return (numberOfRows > 0);
    }

    private Comment mapRowToComment(SqlRowSet rs) {
        Comment comment = new Comment();
        comment.setId(rs.getInt("id"));
        comment.setCommentText(rs.getString("comment_text"));
        comment.setReviewId(rs.getInt("review_id"));
        comment.setUserId(rs.getInt("user_id"));
        return comment;
    }

}
