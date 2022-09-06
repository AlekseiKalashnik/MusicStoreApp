package project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.entity.Visitor;

@Repository
public interface VisitorRepository extends JpaRepository<Visitor, Integer> {
}
