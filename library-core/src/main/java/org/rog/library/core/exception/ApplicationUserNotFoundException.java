package org.rog.library.core.exception;

public class ApplicationUserNotFoundException extends RuntimeException {
    public ApplicationUserNotFoundException(String login){
        super("User with login %s is not found.".formatted(login));
    }
}
