package org.rog.library.core.service;

import org.rog.library.core.entity.Author;

import java.util.List;

public interface AuthorService {
    List<Author> findAllAuthors();
    Author findAuthorById(Long id);
    Author saveAuthor(Author author);
    Author updateAuthor(Author author);
    void deleteAuthor(Long id);
}
