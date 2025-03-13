package org.rog.library.core.exception;

public class AuthorNotFoundException extends RuntimeException {
    public AuthorNotFoundException(Long authorId){
        super("Author with id %d is not found".formatted(authorId));
    }
}
