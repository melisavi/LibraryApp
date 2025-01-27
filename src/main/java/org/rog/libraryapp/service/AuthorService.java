package org.rog.libraryapp.service;

import org.rog.libraryapp.entity.Author;

import java.util.List;

public interface AuthorService {
    List<Author> findAllAuthors();
    Author findAuthorById(Long id);
    Author saveAuthor(Author author);
    Author updateAuthor(Author author);
    void deleteAuthor(Long id);
}
