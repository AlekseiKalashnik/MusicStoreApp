package project.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import project.entity.Album;
import project.entity.Artist;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ArtistDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ArtistDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Artist> showAllArtists() {
        return jdbcTemplate.query("SELECT * FROM Artist", new BeanPropertyRowMapper<>(Artist.class));
    }

    public Optional<Artist> showArtistByNickname(String nickname) {
        return jdbcTemplate.query("SELECT * FROM Artist WHERE artist_nickname=?",
                        new Object[]{nickname}, new BeanPropertyRowMapper<>(Artist.class))
                .stream()
                .findAny();
    }

    public List<Album> showAlbumsByArtistId(int id) {
        return jdbcTemplate.query("SELECT * FROM Album WHERE person_id=?", new Object[] {id} ,
                new BeanPropertyRowMapper<>(Album.class));
    }

    public Artist showArtistById(int id) {
        return jdbcTemplate.query("SELECT * FROM Artist WHERE id=?",
                        new Object[]{id}, new BeanPropertyRowMapper<>(Artist.class))
                .stream()
                .findAny()
                .orElse(null);

    }

    public void save(Artist artist) {
        jdbcTemplate.update("INSERT INTO Artist(artist_name, artist_nickname) VALUES(?, ?)",
                artist.getArtistName(), artist.getArtistNickname());
    }

    public void update(int id, Artist updatedArtist) {
        jdbcTemplate.update("UPDATE Artist SET artist_name=?, artist_nickname=? WHERE id=?",
                updatedArtist.getArtistName(),
                updatedArtist.getArtistNickname(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Artist WHERE id=?", id);
    }

}
