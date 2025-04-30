package org.rog.library.core.service;

import org.rog.library.core.entity.ApplicationUser;
import org.rog.library.core.entity.Book;

import java.util.List;

public interface UserService {

    void save(ApplicationUser applicationUser);

    List<Book> findAllBooksByUser(String login);

    void takeBookOn(Long bookId, String login);

    void takeBookOff(Long bookId, String login);
}
