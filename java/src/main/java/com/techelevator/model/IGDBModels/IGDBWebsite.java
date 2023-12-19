package com.techelevator.model.IGDBModels;

import java.util.Objects;

public class IGDBWebsite {

    private int id;
    private int category;
    private String url;

    public IGDBWebsite(int id, int category, String url) {
        this.id = id;
        this.category = category;
        this.url = url;
    }

    public IGDBWebsite() {
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "IGDBWebsite{" +
                "id=" + id +
                ", category=" + category +
                ", url='" + url + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IGDBWebsite that = (IGDBWebsite) o;
        return id == that.id && category == that.category && Objects.equals(url, that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, category, url);
    }
}
