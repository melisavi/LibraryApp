package org.rog.library.core.exception;

public class AuthorAlreadyExistsException extends RuntimeException{
    public AuthorAlreadyExistsException(String firstName, String lastName, String middleName) {
        super("Author with firstName: %s, lastName: %s, middleName: %s is already exists.".
                formatted(firstName, lastName, middleName));
    }
}
