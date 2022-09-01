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

    @Transactional(readOnly = true)
    public Optional<Visitor> getVisitorBySurname(String surname) {
        Session session = sessionFactory.getCurrentSession();
        Visitor visitor = session.get(Visitor.class, surname);
        return Optional.ofNullable(visitor);
    }

    public List<Album> getAlbumsByVisitorId(int id) {
        return null;
    }

    @Transactional(readOnly = true)
    public Visitor getVisitorById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Visitor.class, id);
    }

    @Transactional
    public void save(Visitor visitor) {
        Session session = sessionFactory.getCurrentSession();
        session.save(visitor);
    }

    @Transactional
    public void update(int id, Visitor visitor) {
        Session session = sessionFactory.getCurrentSession();
        Visitor visitorToUpdate = session.get(Visitor.class, id);
        visitorToUpdate.setVisitorName(visitor.getVisitorName());
        visitorToUpdate.setVisitorSurname(visitor.getVisitorSurname());
        visitorToUpdate.setVisitorBalance(visitor.getVisitorBalance());
    }

    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(Visitor.class, id));
    }
}
