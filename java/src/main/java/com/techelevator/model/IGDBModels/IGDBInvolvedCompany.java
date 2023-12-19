package com.techelevator.model.IGDBModels;

import java.util.Objects;

public class IGDBInvolvedCompany {

    private IGDBCompany company;
    private boolean developer;
    private boolean publisher;

    public IGDBInvolvedCompany(IGDBCompany company, boolean developer, boolean publisher) {
        this.company = company;
        this.developer = developer;
        this.publisher = publisher;
    }

    public IGDBInvolvedCompany() {

    }

    public IGDBCompany getCompany() {
        return company;
    }

    public void setCompany(IGDBCompany company) {
        this.company = company;
    }

    public boolean isDeveloper() {
        return developer;
    }

    public void setDeveloper(boolean developer) {
        this.developer = developer;
    }

    public boolean isPublisher() {
        return publisher;
    }

    public void setPublisher(boolean publisher) {
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        return "IGDBInvolvedCompany{" +
                ", company=" + company +
                ", developer=" + developer +
                ", publisher=" + publisher +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IGDBInvolvedCompany that = (IGDBInvolvedCompany) o;
        return developer == that.developer && publisher == that.publisher && Objects.equals(company, that.company);
    }

    @Override
    public int hashCode() {
        return Objects.hash(company, developer, publisher);
    }
}
