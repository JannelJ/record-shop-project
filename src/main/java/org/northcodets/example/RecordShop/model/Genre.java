package org.northcodets.example.RecordShop.model;

public enum Genre {
    ROCK("Rock"),
    POP("Pop"),
    JAZZ("Jazz"),
    HIPHOP("Hip-Hop"),
    INDIE("Indie");

     String genreDescriptor;

    Genre(String descriptor){
        this.genreDescriptor = descriptor;
    }
}
