package com.techelevator.model.IGDBModels;

import java.util.Objects;

public class IGDBCompany {

    private int id;
    private IGDBLogo logo;
    private String name;
    private String description;

    public IGDBCompany(int id, IGDBLogo logo, String name, String description) {
        this.id = id;
        this.logo = logo;
        this.name = name;
        this.description = description;
    }

    public IGDBCompany() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public IGDBLogo getLogo() {
        return logo;
    }

    public void setLogo(IGDBLogo logo) {
        this.logo = logo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "IGDBCompany{" +
                "id=" + id +
                ", logo='" + logo + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IGDBCompany that = (IGDBCompany) o;
        return id == that.id && Objects.equals(logo, that.logo) && Objects.equals(name, that.name) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, logo, name, description);
    }
}
