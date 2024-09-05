package org.northcodets.example.RecordShop.repository;


import org.northcodets.example.RecordShop.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> { //entity and primary key

}
