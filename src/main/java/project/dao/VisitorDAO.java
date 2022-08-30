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
public class VisitorDAO {
    @Autowired
    public VisitorDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Visitor> getAllVisitors() {
        return jdbcTemplate.query("SELECT * FROM Visitor", new BeanPropertyRowMapper<>(Visitor.class));
    }

    public Optional<Visitor> getVisitorBySurname(String surname) {
        return jdbcTemplate.query("SELECT * FROM Visitor WHERE visitor_surname=?",
                        new Object[]{surname}, new BeanPropertyRowMapper<>(Visitor.class))
                .stream()
                .findAny();
    }

    public List<Album> getAlbumsByVisitorId(int id) {
        return jdbcTemplate.query("SELECT * FROM Album WHERE visitor_id=?", new Object[]{id},
                new BeanPropertyRowMapper<>(Album.class));
    }

    public Visitor getVisitorById(int id) {
        return jdbcTemplate.query("SELECT * FROM Visitor WHERE id=?",
                        new Object[]{id}, new BeanPropertyRowMapper<>(Visitor.class))
                .stream()
                .findAny()
                .orElse(null);

    }

    public void save(Visitor visitor) {
        jdbcTemplate.update("INSERT INTO Visitor(visitor_name, visitor_surname, visitor_balance) VALUES(?, ?, ?)",
                visitor.getVisitorName(), visitor.getVisitorSurname(), visitor.getVisitorBalance());
    }

    public void update(int id, Visitor visitor) {
        jdbcTemplate.update("UPDATE Visitor SET visitor_name=?, visitor_surname=?, visitor_balance=? WHERE id=?",
                visitor.getVisitorName(), visitor.getVisitorSurname(), visitor.getVisitorBalance(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Visitor WHERE id=?", id);
    }
}
