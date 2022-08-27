package project.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import project.entity.Album;
import project.entity.Visitor;

import java.util.List;
import java.util.Optional;

@Component
public class AlbumDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AlbumDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Album> getAllAlbums() {
        return jdbcTemplate.query("SELECT * FROM Album", new BeanPropertyRowMapper<>(Album.class));
    }

    public Album getCertainAlbum(int id) {
        return jdbcTemplate.query("SELECT * FROM Album WHERE id=?", new Object[]{id},
                        new BeanPropertyRowMapper<>(Album.class))
                .stream()
                .findAny()
                .orElse(null);
    }

    public void saveAlbum(Album album) {
        jdbcTemplate.update("INSERT INTO Album(album_name, album_year, artist_name, " +
                        "album_genre, album_price) VALUES(?, ?, ?, ?, ?)",
                album.getAlbumName(), album.getAlbumYear(), album.getArtistName(),
                album.getAlbumGenre(), album.getAlbumPrice());
    }

    public void updateAlbum(int id, Album album) {
        jdbcTemplate.update("UPDATE Album SET album_name=?, album_year=?, artist_name=?, " +
                        "album_genre=?, album_price=? WHERE id=?",
                album.getAlbumName(), album.getAlbumYear(), album.getArtistName(),
                album.getAlbumGenre(), album.getAlbumPrice(), id);

    }

    public void deleteAlbum(int id) {
        jdbcTemplate.update("DELETE FROM Album WHERE id=?", id);
    }

    public Optional<Visitor> getAlbumOwner(int id) {
        return jdbcTemplate.query("SELECT Visitor.* FROM Album JOIN Visitor ON Album.visitor_id = Visitor.id " +
                        "WHERE Album.id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Visitor.class))
                .stream().findAny();
    }

    public void releaseAlbum(int id) {
        jdbcTemplate.update("UPDATE Album SET visitor_id=NULL WHERE id=?", id);
    }

    public void assignAlbum(int id, Visitor visitor) {
        jdbcTemplate.update("UPDATE Album SET visitor_id=? WHERE id=?", visitor.getId(), id);
    }
}
