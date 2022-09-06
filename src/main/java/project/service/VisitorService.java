package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.entity.Visitor;
import project.repositories.VisitorRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class VisitorService {

    private final VisitorRepository visitorRepository;

    @Autowired
    public VisitorService(VisitorRepository visitorRepository) {
        this.visitorRepository = visitorRepository;
    }

    public List<Visitor> findAllVisitors() {
        return visitorRepository.findAll();
    }

    public Visitor findVisitorById(int id) {
        Optional<Visitor> foundVisitor = visitorRepository.findById(id);
        return foundVisitor.orElse(null);
    }

    @Transactional
    public void save(Visitor visitor) {
        visitorRepository.save(visitor);
    }

    @Transactional
    public void update(int id, Visitor visitor) {
        visitor.setId(id);
        visitorRepository.save(visitor);
    }

    @Transactional
    public void delete(int id) {
        visitorRepository.deleteById(id);
    }
}
