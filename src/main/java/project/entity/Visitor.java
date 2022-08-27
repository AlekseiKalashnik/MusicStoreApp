package project.entity;

import javax.validation.constraints.*;

public class Visitor {
    private int id;

    @NotEmpty(message = "Name shouldn't be empty")
    @Size(min = 1, max = 100, message = "Name should be between 1 and 42 characters")
    private String visitorName;

    @NotEmpty(message = "Nickname shouldn't be empty")
    @Size(min = 1, max = 100, message = "Nickname should be between 1 and 42 characters")
    private String visitorSurname;

    @Min(value = 0, message = "Balance should be > 0")
    private int visitorBalance;

    public Visitor() {
    }

    public Visitor(String visitorName, String visitorSurname, int visitorBalance) {
        this.visitorName = visitorName;
        this.visitorSurname = visitorSurname;
        this.visitorBalance = visitorBalance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVisitorName() {
        return visitorName;
    }

    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
    }

    public String getVisitorSurname() {
        return visitorSurname;
    }

    public void setVisitorSurname(String visitorSurname) {
        this.visitorSurname = visitorSurname;
    }

    public int getVisitorBalance() {
        return visitorBalance;
    }

    public void setVisitorBalance(int visitorBalance) {
        this.visitorBalance = visitorBalance;
    }
}