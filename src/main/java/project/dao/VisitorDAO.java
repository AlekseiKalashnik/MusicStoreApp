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
public class VisitorDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public VisitorDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Visitor> getAllVisitors() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Visitor", Visitor.class).getResultList();
    }

    public Optional<Visitor> getVisitorBySurname(String surname) {
        return null;
    }

    public List<Album> getAlbumsByVisitorId(int id) {
        return null;
    }

    public Visitor getVisitorById(int id) {
        return null;
    }

    public void save(Visitor visitor) {
    }

    public void update(int id, Visitor visitor) {
    }

    public void delete(int id) {
    }
}
