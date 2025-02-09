package org.rog.libraryapp.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AuthorExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorExceptionHandler.class);

    @ExceptionHandler(AuthorAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleAuthorAlreadyExistsException(AuthorAlreadyExistsException e){
        LOGGER.error(e.getMessage());
        LOGGER.error(e.getStackTrace().toString());
        return e.getMessage();
    }

    @ExceptionHandler(AuthorNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handlerAuthorNotFoundException(AuthorNotFoundException e){
        LOGGER.error(e.getMessage());
        LOGGER.error(e.getStackTrace().toString());
        return e.getMessage();
    }
}
