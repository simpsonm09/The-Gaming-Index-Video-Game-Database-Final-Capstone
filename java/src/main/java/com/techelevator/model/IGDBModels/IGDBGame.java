package com.techelevator.model.IGDBModels;

import java.util.*;

public class IGDBGame {

    private int id;
    private String name;
    private String summary;
    private int first_release_date;
    private IGDBRating[] age_ratings;
    private IGDBReleaseDate[] release_dates;
    private IGDBCover cover;
    private IGDBPlatform[] platforms;
    private IGDBGenre[] genres;
    private IGDBInvolvedCompany[] involved_companies;
    private IGDBWebsite[] websites;

    public IGDBGame(int id, String name, String summary, int first_release_date, IGDBRating[] age_ratings, IGDBReleaseDate[] release_dates, IGDBCover cover, IGDBPlatform[] platforms, IGDBGenre[] genres, IGDBInvolvedCompany[] involved_companies, IGDBWebsite[] websites) {
        this.id = id;
        this.name = name;
        this.summary = summary;
        this.first_release_date = first_release_date;
        this.age_ratings = age_ratings;
        this.release_dates = release_dates;
        this.cover = cover;
        this.platforms = platforms;
        this.genres = genres;
        this.involved_companies = involved_companies;
        this.websites = websites;
    }

    public IGDBGame() {

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

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public IGDBRating[] getAge_ratings() {
        return age_ratings;
    }

    public int getFirst_release_date() {
        return first_release_date;
    }

    public void setFirst_release_date(int first_release_date) {
        this.first_release_date = first_release_date;
    }

    public int getESRBRating() {
        int ESRBRating = 0;
        for (IGDBRating rating : age_ratings) {
            if (rating.getCategory() == 1) {
                ESRBRating = rating.getRating();
                break;
            }
        }
        return ESRBRating;
    }

    public void setAge_ratings(IGDBRating[] age_ratings) {
        this.age_ratings = age_ratings;
    }

    public IGDBReleaseDate[] getRelease_dates() {
        return release_dates;
    }

    public int getOurReleaseDate() {
        for (IGDBReleaseDate releaseDate : release_dates) {
            if (releaseDate.getRegion() == 2) {
                return releaseDate.getDate();
            }
        }
        for (IGDBReleaseDate releaseDate : release_dates) {
            if (releaseDate.getRegion() == 8) {
                return releaseDate.getDate();
            }
        }
        return 0;
    }

    public void setRelease_dates(IGDBReleaseDate[] release_dates) {
        this.release_dates = release_dates;
    }

    public IGDBCover getCover() {
        return cover;
    }

    public String getCoverURL() {
        return cover.getCoverUrl();
    }

    public void setCover(IGDBCover cover) {
        this.cover = cover;
    }

    public IGDBPlatform[] getPlatforms() {
        return platforms;
    }

    public void setPlatforms(IGDBPlatform[] platforms) {
        this.platforms = platforms;
    }

    public IGDBGenre[] getGenres() {
        return genres;
    }

    public void setGenres(IGDBGenre[] genres) {
        this.genres = genres;
    }

    public IGDBInvolvedCompany[] getInvolved_companies() {
        return involved_companies;
    }

    public void setInvolved_companies(IGDBInvolvedCompany[] involved_companies) {
        this.involved_companies = involved_companies;
    }

    public IGDBWebsite[] getWebsites() {
        return websites;
    }

    public List<String> getWebsitesURLs() {
        List<String> websiteUrls = new ArrayList<>();
        for (IGDBWebsite website : websites) {
            websiteUrls.add(website.getUrl());
        }
        return websiteUrls;
    }

    public void setWebsites(IGDBWebsite[] websites) {
        this.websites = websites;
    }

    @Override
    public String toString() {
        return "IGDBGame{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", summary='" + summary + '\'' +
                ", first_release_date=" + first_release_date +
                ", age_ratings=" + Arrays.toString(age_ratings) +
                ", release_dates=" + Arrays.toString(release_dates) +
                ", cover=" + cover +
                ", platforms=" + Arrays.toString(platforms) +
                ", genres=" + Arrays.toString(genres) +
                ", involved_companies=" + Arrays.toString(involved_companies) +
                ", websites=" + Arrays.toString(websites) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IGDBGame igdbGame = (IGDBGame) o;
        return id == igdbGame.id && first_release_date == igdbGame.first_release_date && Objects.equals(name, igdbGame.name) && Objects.equals(summary, igdbGame.summary) && Arrays.equals(age_ratings, igdbGame.age_ratings) && Arrays.equals(release_dates, igdbGame.release_dates) && Objects.equals(cover, igdbGame.cover) && Arrays.equals(platforms, igdbGame.platforms) && Arrays.equals(genres, igdbGame.genres) && Arrays.equals(involved_companies, igdbGame.involved_companies) && Arrays.equals(websites, igdbGame.websites);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, name, summary, first_release_date, cover);
        result = 31 * result + Arrays.hashCode(age_ratings);
        result = 31 * result + Arrays.hashCode(release_dates);
        result = 31 * result + Arrays.hashCode(platforms);
        result = 31 * result + Arrays.hashCode(genres);
        result = 31 * result + Arrays.hashCode(involved_companies);
        result = 31 * result + Arrays.hashCode(websites);
        return result;
    }
}
