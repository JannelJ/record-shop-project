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
    public Album createAlbum(Album album) { // when an album is added
        album.setInStock(album.getStockCount() > 0); // in stock if count > 0
        return albumRepository.save(album);
    }

    @Override
    public Album saveAlbum(Album album) { // so created album is saved in repo
        return albumRepository.save(album);
    }

    @Override
    public Album updateAlbum(Long id, Album album) {

        Album existingAlbum = getAlbumById(id);

        existingAlbum.setAlbumName(album.getAlbumName());
        existingAlbum.setArtist(album.getArtist());
        existingAlbum.setReleaseDate(album.getReleaseDate());
        existingAlbum.setPrice(album.getPrice());
        existingAlbum.setGenre(album.getGenre());
        existingAlbum.setInStock(album.isInStock()); // boolean
        existingAlbum.setStockCount(album.getStockCount());

        return albumRepository.save(existingAlbum);
    }

    @Override
    public void deleteAlbum(Long id) {
    albumRepository.deleteById(id);
    }
}
