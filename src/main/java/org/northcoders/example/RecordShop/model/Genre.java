package org.northcoders.example.RecordShop.model;


import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;



public enum Genre {
    ROCK("Rock"),
    POP("Pop"),
    JAZZ("Jazz"),
    HIPHOP("Hip-Hop"),
    INDIE("Indie");

    final String genreDescriptor;

    Genre(String descriptor) {
        this.genreDescriptor = descriptor;
    }


}
