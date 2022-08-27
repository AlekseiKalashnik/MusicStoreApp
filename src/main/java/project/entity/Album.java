package project.entity;

import javax.validation.constraints.*;

public class Album {

    private int id;
    @NotEmpty(message = "Album name shouldn't be empty")
    @Size(min = 2, max = 100, message = "Album name should be between 2 and 100 characters")
    private String albumName;

    @Min(value = 1900, message = "Year should be after 1900")
    private int albumYear;

    @NotEmpty(message = "Artist name shouldn't be empty")
    @Size(min = 2, max = 100, message = "Artist name should be between 2 and 100 characters")
    private String artistName;

    @NotEmpty(message = "Genre shouldn't be empty")
    private String albumGenre;

    @Min(value = 0, message = "Price should be > 0")
    private int albumPrice;

    public Album() {
    }

    public Album(String albumName, int albumYear, String artistName, String albumGenre, int albumPrice) {
        this.albumName = albumName;
        this.albumYear = albumYear;
        this.artistName = artistName;
        this.albumGenre = albumGenre;
        this.albumPrice = albumPrice;
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

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getAlbumGenre() {
        return albumGenre;
    }

    public void setAlbumGenre(String albumGenre) {
        this.albumGenre = albumGenre;
    }

    public int getAlbumPrice() {
        return albumPrice;
    }

    public void setAlbumPrice(int albumPrice) {
        this.albumPrice = albumPrice;
    }
}