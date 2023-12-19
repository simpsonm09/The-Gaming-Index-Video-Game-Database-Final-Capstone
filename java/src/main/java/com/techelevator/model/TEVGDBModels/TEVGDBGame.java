package com.techelevator.model.TEVGDBModels;

import com.techelevator.model.IGDBModels.*;

import java.util.*;

public class TEVGDBGame {

    private int id;
    private String name;
    private int esrbRating;
    private int releaseDate;
    private String coverURL;
    private String summary;
    private List<IGDBWebsite> websites;
    private List<IGDBPlatform> platforms;
    private List<IGDBGenre> genres;
    private List<TEVGDBInvolvedCompany> involved_companies;

    public TEVGDBGame(int id, String name, int esrbRating, int releaseDate, String coverURL, String summary, List<IGDBWebsite> websites, List<IGDBPlatform> platforms, List<IGDBGenre> genres, List<TEVGDBInvolvedCompany> involved_companies) {
        this.id = id;
        this.name = name;
        this.esrbRating = esrbRating;
        this.releaseDate = releaseDate;
        this.coverURL = coverURL;
        this.summary = summary;
        this.websites = websites;
        this.platforms = platforms;
        this.genres = genres;
        this.involved_companies = involved_companies;
    }

    public TEVGDBGame(IGDBGame game) {
        this.id = game.getId();
        this.name = game.getName();
        if (game.getAge_ratings() != null) {
            this.esrbRating = game.getESRBRating();
        }
        if (game.getFirst_release_date() != 0) {
            this.releaseDate = game.getFirst_release_date();
        } else if (game.getRelease_dates() != null) {
            this.releaseDate = game.getOurReleaseDate();
        }
        if (game.getCover() != null) {
            this.coverURL = game.getCoverURL();
        }
        this.summary = game.getSummary();
        if (game.getWebsites() != null) {
            this.websites = Arrays.asList(game.getWebsites());
        }
        if (game.getPlatforms() != null) {
            this.platforms = Arrays.asList(game.getPlatforms());
        }
        if (game.getGenres() != null) {
            this.genres = Arrays.asList(game.getGenres());
        }
        if (game.getInvolved_companies() != null) {
            List<TEVGDBInvolvedCompany> TEinvolvedCompanies = new ArrayList<>();
            for (int i = 0; i < game.getInvolved_companies().length; i++) {
                TEinvolvedCompanies.add(new TEVGDBInvolvedCompany(game.getInvolved_companies()[i]));
            }
            this.involved_companies = TEinvolvedCompanies;
        }
    }

    public TEVGDBGame() {
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

    public int getEsrbRating() {
        return esrbRating;
    }

    public void setEsrbRating(int esrbRating) {
        this.esrbRating = esrbRating;
    }

    public int getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(int releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getCoverURL() {
        return coverURL;
    }

    public void setCoverURL(String coverURL) {
        this.coverURL = coverURL;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public List<IGDBWebsite> getWebsites() {
        return websites;
    }

    public void setWebsites(List<IGDBWebsite> websites) {
        this.websites = websites;
    }

    public List<IGDBPlatform> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(List<IGDBPlatform> platforms) {
        this.platforms = platforms;
    }

    public List<IGDBGenre> getGenres() {
        return genres;
    }

    public void setGenres(List<IGDBGenre> genres) {
        this.genres = genres;
    }

    public List<TEVGDBInvolvedCompany> getInvolved_companies() {
        return involved_companies;
    }

    public void setInvolved_companies(List<TEVGDBInvolvedCompany> involved_companies) {
        this.involved_companies = involved_companies;
    }

    @Override
    public String toString() {
        return "TEVGDBGame{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", esrbRating=" + esrbRating +
                ", releaseDate=" + releaseDate +
                ", coverURL='" + coverURL + '\'' +
                ", summary='" + summary + '\'' +
                ", websites=" + websites +
                ", platforms=" + platforms +
                ", genres=" + genres +
                ", involved_companies=" + involved_companies +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TEVGDBGame that = (TEVGDBGame) o;
        return id == that.id && esrbRating == that.esrbRating && Objects.equals(name, that.name) && Objects.equals(releaseDate, that.releaseDate) && Objects.equals(coverURL, that.coverURL) && Objects.equals(summary, that.summary) && Objects.equals(websites, that.websites) && Objects.equals(platforms, that.platforms) && Objects.equals(genres, that.genres) && Objects.equals(involved_companies, that.involved_companies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, esrbRating, releaseDate, coverURL, summary, websites, platforms, genres, involved_companies);
    }
}
