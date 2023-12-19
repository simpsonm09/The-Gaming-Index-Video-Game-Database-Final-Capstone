package com.techelevator.dao;

import com.techelevator.model.Review;

import java.util.List;

public interface ReviewDao {

    List<Review> getReviewsByGameId(int gameId);

    Review getReviewById(int id);

    Review createReview(Review review);

    Review updateReview(Review review);

    boolean deleteReviewById(int id);

}
