package com.techelevator.model.TEVGDBModels;

import com.techelevator.model.IGDBModels.IGDBCompany;

import java.util.Objects;

public class TEVGDBCompany {

    private int id;
    private String logo;
    private String name;
    private String description;

    public TEVGDBCompany(int id, String logo, String name, String description) {
        this.id = id;
        this.logo = logo;
        this.name = name;
        this.description = description;
    }

    public TEVGDBCompany(IGDBCompany company) {
        this.id = company.getId();
        if (company.getLogo() != null) {
            this.logo = company.getLogo().getCompanyLogoUrl();
        }
        this.name = company.getName();
        this.description = company.getDescription();
    }

    public TEVGDBCompany() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
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
        TEVGDBCompany that = (TEVGDBCompany) o;
        return id == that.id && Objects.equals(logo, that.logo) && Objects.equals(name, that.name) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, logo, name, description);
    }
}
