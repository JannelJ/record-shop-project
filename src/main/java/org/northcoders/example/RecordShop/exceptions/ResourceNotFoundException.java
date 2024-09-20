package org.northcoders.example.RecordShop.exceptions;

public class ResourceNotFoundException extends RuntimeException { // custom exception inherits java's runtime exception behaviour

    public ResourceNotFoundException(String message) { // constructor with error message
        super(message); // calls parent class constructor
    }
}
