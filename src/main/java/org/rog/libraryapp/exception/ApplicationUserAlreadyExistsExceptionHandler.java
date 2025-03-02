package org.rog.libraryapp.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationUserAlreadyExistsExceptionHandler {
    private static final org.slf4j.Logger LOGGER = (Logger) LoggerFactory.getLogger(ApplicationUserAlreadyExistsExceptionHandler.class);

    @ExceptionHandler(ApplicationUserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleApplicationUserAlreasyExistsException(ApplicationUserAlreadyExistsException e){
        LOGGER.error(e.getMessage());
        return e.getMessage();
    }

    @ExceptionHandler(ApplicationUserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleApplicationUserNotFoundException(ApplicationUserNotFoundException e){
        LOGGER.error(e.getMessage());
        return e.getMessage();
    }
}
