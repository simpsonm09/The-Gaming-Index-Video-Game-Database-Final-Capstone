package com.techelevator.model;

import java.util.Objects;

public class Review {

    private int id;
    private int numberOfStars;
    private String reviewText;
    private int replayabilityScore;
    private int diversityScore;
    private int aggroScore;
    private int gameId;
    private int userId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumberOfStars() {
        return numberOfStars;
    }

    public void setNumberOfStars(int numberOfStars) {
        this.numberOfStars = numberOfStars;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public int getReplayabilityScore() {
        return replayabilityScore;
    }

    public void setReplayabilityScore(int replayabilityScore) {
        this.replayabilityScore = replayabilityScore;
    }

    public int getDiversityScore() {
        return diversityScore;
    }

    public void setDiversityScore(int diversityScore) {
        this.diversityScore = diversityScore;
    }

    public int getAggroScore() {
        return aggroScore;
    }

    public void setAggroScore(int aggroScore) {
        this.aggroScore = aggroScore;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", numberOfStars=" + numberOfStars +
                ", reviewText='" + reviewText + '\'' +
                ", replayabilityScore=" + replayabilityScore +
                ", diversityScore=" + diversityScore +
                ", aggroScore=" + aggroScore +
                ", gameId=" + gameId +
                ", userId=" + userId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return id == review.id && numberOfStars == review.numberOfStars && replayabilityScore == review.replayabilityScore && diversityScore == review.diversityScore && aggroScore == review.aggroScore && gameId == review.gameId && userId == review.userId && Objects.equals(reviewText, review.reviewText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numberOfStars, reviewText, replayabilityScore, diversityScore, aggroScore, gameId, userId);
    }
}
