package org.northcodets.example.RecordShop.service;


import org.northcodets.example.RecordShop.model.Album;
import org.northcodets.example.RecordShop.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    AlbumRepository albumRepository;


    @Override
    public List<Album> getAllAlbums() {
        return albumRepository.findAll();
    }

    @Override
    public Album getAlbumById(Long id) {
        Optional<Album> album = albumRepository.findById(id);
        return album.orElseThrow(() -> new RuntimeException("Album not found"));
    }

    @Override
    public Album createAlbum(Album album) {
        return albumRepository.save(album);
    }

    @Override
    public Album updateAlbum(Long id, Album album) {

        Album existingAlbum = getAlbumById(id);

        existingAlbum.setTitle(album.getTitle());
        existingAlbum.setArtist(album.getArtist());
        existingAlbum.setReleaseDate(album.getReleaseDate());
        existingAlbum.setPrice(album.getPrice());
        existingAlbum.setGenre(album.getGenre());

        return albumRepository.save(existingAlbum);
    }

    @Override
    public void deleteAlbum(Long id) {
    albumRepository.deleteById(id);
    }
}
