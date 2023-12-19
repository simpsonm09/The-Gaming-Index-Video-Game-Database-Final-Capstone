package com.techelevator.services;

import com.techelevator.dao.ReviewDao;
import com.techelevator.model.Review;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReviewService {

    private final ReviewDao reviewDao;

    public ReviewService(ReviewDao reviewDao) {
        this.reviewDao = reviewDao;
    }

    public List<Review> getReviewsByGameId(int gameId) {
        return reviewDao.getReviewsByGameId(gameId);
    }


    public Review getReviewById(int id) {
        return reviewDao.getReviewById(id);
    }


    public Review createReview(Review review) {
        return reviewDao.createReview(review);
    }


    public Review updateReview(Review review) {
        return reviewDao.updateReview(review);
    }


    public boolean deleteReviewById(int id) {
        return reviewDao.deleteReviewById(id);
    }

}
