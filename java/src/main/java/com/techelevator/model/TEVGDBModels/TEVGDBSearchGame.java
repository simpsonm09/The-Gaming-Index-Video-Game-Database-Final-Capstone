package com.techelevator.model.TEVGDBModels;

import com.techelevator.model.IGDBModels.*;

import java.util.Objects;

public class TEVGDBSearchGame {

    private int id;
    private String name;
    private int releaseDate;
    private String coverURL;

    public TEVGDBSearchGame(int id, String name, int releaseDate, String coverURL) {
        this.id = id;
        this.name = name;
        this.releaseDate = releaseDate;
        this.coverURL = coverURL;
    }

    public TEVGDBSearchGame(IGDBGame game) {
        this.id = game.getId();
        this.name = game.getName();
        if (game.getRelease_dates() != null) {
            this.releaseDate = game.getOurReleaseDate();
        }
        if (game.getCover() != null) {
            this.coverURL = game.getCoverURL();
        }
    }

    public TEVGDBSearchGame() {
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

    @Override
    public String toString() {
        return "TEVGDBGame{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", releaseDate=" + releaseDate +
                ", coverURL='" + coverURL + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TEVGDBSearchGame that = (TEVGDBSearchGame) o;
        return id == that.id && releaseDate == that.releaseDate && Objects.equals(name, that.name) && Objects.equals(coverURL, that.coverURL);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, releaseDate, coverURL);
    }
}
