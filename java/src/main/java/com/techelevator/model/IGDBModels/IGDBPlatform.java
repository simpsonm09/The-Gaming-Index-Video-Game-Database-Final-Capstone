package com.techelevator.model.IGDBModels;

import java.util.Objects;

public class IGDBPlatform {

    private int id;
    private String name;

    public IGDBPlatform(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public IGDBPlatform() {

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
        return "IGDBPlatform{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IGDBPlatform that = (IGDBPlatform) o;
        return id == that.id && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
