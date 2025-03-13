package org.rog.library.core.exception;

public class ApplicationUserAlreadyExistsException extends RuntimeException {
    public ApplicationUserAlreadyExistsException(String login) {
        super("User with login %s is already exists.".formatted(login));
    }
}
