package project.entity;

import javax.validation.constraints.*;

import java.util.List;

public class Album {

    @NotEmpty(message = "Album name shouldn't be empty")
    @Size(min = 2, max = 36, message = "Album name should be between 2 and 36 characters")
    String albumName;

    List<Artist> albumAuthors;

    public Album() {
    }

    public Album(String albumName) {
        this.albumName = albumName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public List<Artist> getAlbumAuthors() {
        return albumAuthors;
    }

    public void setAlbumAuthors(List<Artist> albumAuthors) {
        this.albumAuthors = albumAuthors;
    }
}
