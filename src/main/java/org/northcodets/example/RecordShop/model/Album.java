package org.northcodets.example.RecordShop.model;


import jakarta.persistence.*;
import lombok.Data;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Entity
@Data
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // primary key + auto-increments
    private Long id;

    private String title;
    private String artist;

    private LocalDate releaseDate;

    private double price;

    @Enumerated(EnumType.STRING)
    private Genre genre;
}
