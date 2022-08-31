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


    public List<Album> getAllAlbums() {
        return null;
    }

    public Album getCertainAlbum(int id) {
        return null;
    }

    public void saveAlbum(Album album) {

    }

    public void updateAlbum(int id, Album album) {

    }

    public void deleteAlbum(int id) {
    }

    public Optional<Visitor> getAlbumOwner(int id) {
        return null;
    }

    public void releaseAlbum(int id) {
    }

    public void assignAlbum(int id, Visitor visitor) {
    }
}
