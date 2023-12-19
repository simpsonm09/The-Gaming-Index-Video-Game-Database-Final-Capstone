package com.techelevator.dao;

import com.techelevator.model.Comment;

import java.util.List;

public interface CommentDao {

    List<Comment> getCommentsByReviewId(int reviewId);

    Comment getCommentById(int id);

    Comment createComment(Comment comment);

    Comment updateComment(Comment comment);

    boolean deleteCommentById(int id);

}
