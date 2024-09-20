package org.northcoders.example.RecordShop.repository;


import org.northcoders.example.RecordShop.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> { //entity and primary key

}
