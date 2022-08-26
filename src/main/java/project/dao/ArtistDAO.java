package project.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
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
        return jdbcTemplate.query("SELECT * FROM Artist WHERE nickname=?",
                new Object[]{nickname}, new BeanPropertyRowMapper<>(Artist.class))
                .stream()
                .findAny();
    }

    public Artist showArtistById(int id) {
        return jdbcTemplate.query("SELECT * FROM Artist WHERE id=?",
                        new Object[]{id}, new BeanPropertyRowMapper<>(Artist.class))
                .stream()
                .findAny()
                .orElse(null);

    }

    public void save(Artist artist) {
        jdbcTemplate.update("INSERT INTO Artist(name, nickname) VALUES(?, ?)", artist.getName(), artist.getNickname());
    }

    public void update(int id, Artist updatedArtist) {
        jdbcTemplate.update("UPDATE Artist SET name=?, nickname=? WHERE id=?", updatedArtist.getName(),
                updatedArtist.getNickname(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Artist WHERE id=?", id);
    }

    ////////////////////////////
    /*Простая вставка*/

    public void testMultipleUpdate() {
        List<Artist> artists = create1000people();

        long before = System.currentTimeMillis();

        for (Artist artist : artists) {
            jdbcTemplate.update("INSERT INTO Artist VALUES(?, ?, ?)",
                    artist.getId(), artist.getName(), artist.getNickname());
        }

        long after = System.currentTimeMillis();
        System.out.println("Time: " + (after - before));

    }

    /*Пакетная вставка*/
    public void testBatchUpdate() {
        List<Artist> artists = create1000people();
        long before = System.currentTimeMillis();

        jdbcTemplate.batchUpdate("INSERT INTO Artist VALUES(?, ?, ?)", new BatchPreparedStatementSetter() {
            //передаем значения, которые попадут в наш пакет
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                preparedStatement.setInt(1, artists.get(i).getId());
                preparedStatement.setString(2, artists.get(i).getName());
                preparedStatement.setString(3, artists.get(i).getNickname());
            }

            //возвращем размер нашего пакета
            @Override
            public int getBatchSize() {
                return artists.size();
            }
        });

        long after = System.currentTimeMillis();
        System.out.println("Time: " + (after - before));

    }

    private List<Artist> create1000people() {
        List<Artist> artists = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            artists.add(new Artist(i, "Name - " + i, "Nickname - " + i));
        }

        return artists;
    }
}
