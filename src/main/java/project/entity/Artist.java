package project.entity;

import javax.validation.constraints.*;

public class Artist {
    private int id;

    @NotEmpty(message = "Name shouldn't be empty")
    @Size(min = 1, max = 42, message = "Name should be between 1 and 42 characters")
    private String artistName;

    @NotEmpty(message = "Nickname shouldn't be empty")
    @Size(min = 1, max = 42, message = "Nickname should be between 1 and 42 characters")
    private String artistNickname;

    public Artist() {
    }

    public Artist(int id, String artistName, String artistNickname) {
        this.id = id;
        this.artistName = artistName;
        this.artistNickname = artistNickname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getArtistNickname() {
        return artistNickname;
    }

    public void setArtistNickname(String artistNickname) {
        this.artistNickname = artistNickname;
    }
}
