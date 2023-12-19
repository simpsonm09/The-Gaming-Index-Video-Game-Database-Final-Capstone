package com.techelevator.services;


import com.techelevator.dao.CommentDao;
import com.techelevator.model.Comment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommentService {

    private final CommentDao commentDao;

    public CommentService(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    public List<Comment> getCommentsByReviewId(int reviewId) {
        return commentDao.getCommentsByReviewId(reviewId);
    }

    public Comment getCommentById(int id) {
        return commentDao.getCommentById(id);
    }

    public Comment createComment(Comment comment) {
        return commentDao.createComment(comment);
    }

    public Comment updateComment(Comment comment) {
        return commentDao.updateComment(comment);
    }

    public boolean deleteCommentById(int id) {
        return commentDao.deleteCommentById(id);
    }

}
