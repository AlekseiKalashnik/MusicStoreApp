package project.entity;

import javax.validation.constraints.*;

public class Artist {
    private int id;

    @NotEmpty(message = "Name shouldn't be empty")
    @Size(min = 1, max = 42, message = "Name should be between 1 and 42 characters")
    private String name;

    @NotEmpty(message = "Nickname shouldn't be empty")
    @Size(min = 1, max = 36, message = "Nickname should be between 1 and 36 characters")
    private String nickname;

    public Artist() {
    }

    public Artist(int id, String name, String nickname) {
        this.id = id;
        this.name = name;
        this.nickname = nickname;
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
