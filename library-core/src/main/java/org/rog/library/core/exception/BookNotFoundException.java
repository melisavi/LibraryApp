package org.rog.library.core.exception;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(Long id) {
        super("Book with id %s is not found.".formatted(id));
    }
}
