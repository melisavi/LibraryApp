package org.rog.library.core.exception.handler;

import org.rog.library.core.exception.BookUnavailableException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionHandler {
    private  static final Logger LOGGER = LoggerFactory.getLogger(UserExceptionHandler.class);

    @ExceptionHandler(BookUnavailableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleBookUnavailableException(BookUnavailableException e){
        LOGGER.error(e.getMessage());
        e.printStackTrace();
        return e.getMessage();
    }
}
