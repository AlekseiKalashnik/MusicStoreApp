package project.entity;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import javax.persistence.*;


@Entity
@Table(name = "album")
public class Album {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty(message = "Album name shouldn't be empty")
    @Column(name = "album_name")
    private String albumName;

    @Min(value = 1900, message = "Year should be after 1900")
    @Column(name = "album_year")
    private int albumYear;

    @NotEmpty(message = "Genre shouldn't be empty")
    @Column(name = "album_genre")
    private String albumGenre;

    @Min(value = 0, message = "Price should be > 0")
    @Column(name = "album_price")
    private int albumPrice;

    @ManyToMany(mappedBy = "albums")
    private List<Visitor> visitors;

    public Album() {
    }

    public Album(String albumName, int albumYear, String albumGenre, int albumPrice) {
        this.albumName = albumName;
        this.albumYear = albumYear;
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

    @Override
    public String toString() {
        return "Album{" +
                "albumName='" + albumName + '\'' +
                ", albumYear=" + albumYear +
                ", albumGenre='" + albumGenre + '\'' +
                ", albumPrice=" + albumPrice +
                '}';
    }
}