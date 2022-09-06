package project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.entity.Album;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Integer> {
}
