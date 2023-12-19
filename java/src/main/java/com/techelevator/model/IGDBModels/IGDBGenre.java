package com.techelevator.model.IGDBModels;

import java.util.Objects;

public class IGDBGenre {

    private int id;
    private String name;

    public IGDBGenre(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public IGDBGenre() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "IGDBGenre{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IGDBGenre igdbGenre = (IGDBGenre) o;
        return id == igdbGenre.id && Objects.equals(name, igdbGenre.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
