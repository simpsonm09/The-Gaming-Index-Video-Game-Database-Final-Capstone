package com.techelevator.model.TEVGDBModels;

import com.techelevator.model.IGDBModels.IGDBInvolvedCompany;

import java.util.Objects;

public class TEVGDBInvolvedCompany {

    private TEVGDBCompany company;
    private boolean developer;
    private boolean publisher;

    public TEVGDBInvolvedCompany(TEVGDBCompany company, boolean developer, boolean publisher) {
        this.company = company;
        this.developer = developer;
        this.publisher = publisher;
    }

    public TEVGDBInvolvedCompany(IGDBInvolvedCompany involvedCompany) {
        this.company = new TEVGDBCompany(involvedCompany.getCompany());
        this.developer = involvedCompany.isDeveloper();
        this.publisher = involvedCompany.isPublisher();
    }

    public TEVGDBInvolvedCompany() {

    }

    public TEVGDBCompany getCompany() {
        return company;
    }

    public void setCompany(TEVGDBCompany company) {
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
        TEVGDBInvolvedCompany that = (TEVGDBInvolvedCompany) o;
        return developer == that.developer && publisher == that.publisher && Objects.equals(company, that.company);
    }

    @Override
    public int hashCode() {
        return Objects.hash(company, developer, publisher);
    }
}

