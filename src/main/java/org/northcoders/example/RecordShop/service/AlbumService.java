package org.northcoders.example.RecordShop.service;

import org.northcoders.example.RecordShop.model.Album;

import java.util.List;

public interface AlbumService {


    List<Album> getAllAlbums();
    Album getAlbumById(Long id);
    Album createAlbum(Album album);
    Album saveAlbum(Album album);
    Album updateAlbum(Long id, Album album);
    void deleteAlbum(Long id);
}
