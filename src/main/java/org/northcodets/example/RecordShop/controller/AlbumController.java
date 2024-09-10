package org.northcodets.example.RecordShop.controller;


import org.northcodets.example.RecordShop.model.Album;
import org.northcodets.example.RecordShop.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/albums")
        public class AlbumController {

    @Autowired
    private AlbumService albumService;

    // GET all albums.
    @GetMapping
    public ResponseEntity<List<Album>> getAllAlbums() {
        List<Album> albums = albumService.getAllAlbums();
        return new ResponseEntity<>(albums, HttpStatus.OK);

    }
    // GET album by ID
    @GetMapping ("/{id}")
    public ResponseEntity<Album> getAlbumById(@PathVariable Long id) {
        try {
            Album album = albumService.getAlbumById(id);
            return new ResponseEntity<>(album, HttpStatus.OK);

        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping // create a new album with POST
    public ResponseEntity<Album> createAlbum(@RequestBody Album album) {
        Album createdAlbum = albumService.createAlbum(album);
        return new ResponseEntity<>(createdAlbum, HttpStatus.CREATED);
    }


}
