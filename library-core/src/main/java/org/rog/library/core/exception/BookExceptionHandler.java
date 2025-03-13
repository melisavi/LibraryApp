package org.rog.library.core.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BookExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(BookExceptionHandler.class);

    @ExceptionHandler(BookNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handlerBookNotFoundException(BookNotFoundException e){
        LOGGER.error(e.getMessage());
        LOGGER.error(e.getStackTrace().toString());
        return e.getMessage();
    }
}
