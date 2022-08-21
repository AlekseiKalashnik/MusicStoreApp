package project.entity;

import javax.validation.constraints.*;

public class Disc {

    @NotEmpty(message = "Name shouldn't be empty")
    @Size(min = 2, max = 36, message = "Name should be between 2 and 36 characters")
    String name;

    @NotEmpty(message = "Genre name shouldn't be empty")
    @Size(min = 2, max = 36, message = "Genre name should be between 2 and 36 characters")
    String genre;

    int year;

    public Disc() {
    }

    public Disc(String name, String genre, int year) {
        this.name = name;
        this.genre = genre;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
