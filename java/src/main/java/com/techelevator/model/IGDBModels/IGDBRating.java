package com.techelevator.model.IGDBModels;

public class IGDBRating {

    private int id;
    private int category;
    private int rating;

    public IGDBRating(int id, int category, int rating) {
        this.id = id;
        this.category = category;
        this.rating = rating;
    }

    public IGDBRating() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
