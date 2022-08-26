package project.entity;

import javax.validation.constraints.*;

import java.util.List;

public class Album {

    private int id;
    @NotEmpty(message = "Album name shouldn't be empty")
    @Size(min = 2, max = 100, message = "Album name should be between 2 and 36 characters")
    private String albumName;

    @Min(value = 1900, message = "Year should be after 1900")
    private int albumYear;

    private Artist artist;

    public Album() {
    }

    public Album(String albumName, int albumYear, Artist artist) {
        this.albumName = albumName;
        this.albumYear = albumYear;
        this.artist = artist;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public int getAlbumYear() {
        return albumYear;
    }

    public void setAlbumYear(int albumYear) {
        this.albumYear = albumYear;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }
}
