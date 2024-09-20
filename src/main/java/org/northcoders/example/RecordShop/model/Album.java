package org.northcoders.example.RecordShop.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // primary key + auto-increments
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
