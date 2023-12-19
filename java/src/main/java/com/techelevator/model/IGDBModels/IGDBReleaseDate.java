package com.techelevator.model.IGDBModels;

import java.util.Objects;

public class IGDBReleaseDate {

    private int date;
    private int region;

    public IGDBReleaseDate(int date, int region) {
        this.date = date;
        this.region = region;
    }

    public IGDBReleaseDate() {
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getRegion() {
        return region;
    }

    public void setRegion(int region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return "IGDBReleaseDate{" +
                "date=" + date +
                ", region=" + region +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IGDBReleaseDate that = (IGDBReleaseDate) o;
        return date == that.date && region == that.region;
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, region);
    }
}
