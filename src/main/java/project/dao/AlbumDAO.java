package project.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import project.entity.Album;
import project.entity.Visitor;

import java.util.List;
import java.util.Optional;

@Component
public class AlbumDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public AlbumDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Album> getAllAlbums() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Album", Album.class).getResultList();
    }

    @Transactional(readOnly = true)
    public Album getCertainAlbum(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Album.class, id);
    }

    @Transactional
    public void saveAlbum(Album album) {
        Session session = sessionFactory.getCurrentSession();
        session.save(album);
    }

    @Transactional
    public void updateAlbum(int id, Album album) {
        Session session = sessionFactory.getCurrentSession();
        Album albumToUpdate = session.get(Album.class, id);
        albumToUpdate.setAlbumName(album.getAlbumName());
        albumToUpdate.setAlbumYear(album.getAlbumYear());
        albumToUpdate.setAlbumGenre(album.getAlbumGenre());
        albumToUpdate.setAlbumPrice(album.getAlbumPrice());
    }

    @Transactional
    public void deleteAlbum(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(Album.class, id));
    }

    public Optional<Visitor> getAlbumOwner(int id) {
        return null;
    }

    public void releaseAlbum(int id) {
    }

    public void assignAlbum(int id, Visitor visitor) {
    }
}
