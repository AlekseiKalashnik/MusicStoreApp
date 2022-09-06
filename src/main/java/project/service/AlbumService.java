package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.entity.Album;
import project.repositories.AlbumRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class AlbumService {

    private final AlbumRepository albumRepository;

    @Autowired
    public AlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    public List<Album> findAllAlbums() {
        return albumRepository.findAll();
    }

    public Album findAlbumById(int id) {
        Optional<Album> foundAlbum = albumRepository.findById(id);
        return foundAlbum.orElse(null);
    }

    @Transactional
    public void save(Album album) {
        albumRepository.save(album);
    }

    @Transactional
    public void update(int id, Album album) {
        album.setId(id);
        albumRepository.save(album);
    }

    @Transactional
    public void delete(int id) {
        albumRepository.deleteById(id);
    }
}
