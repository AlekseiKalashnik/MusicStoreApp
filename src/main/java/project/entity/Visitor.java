package project.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Visitor {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Name shouldn't be empty")
    @Size(min = 1, max = 100, message = "Name should be between 1 and 42 characters")
    @Column(name = "visitor_name")
    private String visitorName;

    @NotEmpty(message = "Nickname shouldn't be empty")
    @Size(min = 1, max = 100, message = "Nickname should be between 1 and 42 characters")
    @Column(name = "visitor_surname")
    private String visitorSurname;

    @Min(value = 0, message = "Balance should be > 0")
    @Column(name = "visitor_balance")
    private int visitorBalance;

    @ManyToMany
    @JoinTable(name = "visitor_album",
    joinColumns = @JoinColumn(name = "visitor_id"),
    inverseJoinColumns = @JoinColumn(name = "album_id"))
    private List<Album> albumsList;

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