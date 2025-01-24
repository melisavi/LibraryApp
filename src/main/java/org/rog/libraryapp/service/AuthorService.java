package org.rog.libraryapp.service;

import org.rog.libraryapp.entity.Author;
import org.rog.libraryapp.entity.Book;

import java.util.List;

public interface AuthorService {
    List<Author> findAllAuthors();
    Author findAuthorById(Long id);
    Long saveAuthor(Author author);
    Author updateAuthor(Author author);
    void deleteAuthor(Long id);
}
