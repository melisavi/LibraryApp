package org.rog.library.core.exception;

public class BookUnavailableException extends RuntimeException {
    public BookUnavailableException(Long id) {
        super("The book with id=%d does not exist or not available for taking on.".formatted(id));
    }
}
