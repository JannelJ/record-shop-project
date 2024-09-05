package org.northcodets.example.RecordShop.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // primary key + auto-increments
    private Long id;

    private String artist;
    private String albumName;

    private LocalDate releaseDate;
    private double price;
    private int stockCount;
    private boolean isInStock;

    @Enumerated(EnumType.STRING)
    private Genre genre;


}
