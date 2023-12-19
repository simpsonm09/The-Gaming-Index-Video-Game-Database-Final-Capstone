package com.techelevator.model.IGDBModels;

import java.util.Objects;

public class IGDBCover {

    private int id;
    private String image_id;
    private String baseUrl = "https://images.igdb.com/igdb/image/upload/t_cover_big/";

    public IGDBCover(int id, String image_id) {
        this.id = id;
        this.image_id = image_id;
    }

    public IGDBCover() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage_id() {
        return image_id;
    }

    public void setImage_id(String image_id) {
        this.image_id = image_id;
    }

    public String getCoverUrl() {
        if (image_id == null) {
            return null;
        }
        return baseUrl + image_id + ".jpg";
    }

    @Override
    public String toString() {
        return "IGDBCover{" +
                "id=" + id +
                ", image_id='" + image_id + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IGDBCover igdbCover = (IGDBCover) o;
        return id == igdbCover.id && Objects.equals(image_id, igdbCover.image_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, image_id);
    }
}
