package org.rog.libraryapp.dao;

import org.rog.libraryapp.entity.Author;
import org.rog.libraryapp.entity.Book;

import java.util.List;
import java.util.Optional;

public interface AuthorDao {
    List<Author> findAllAuthors();
    Optional<Author> findAuthorById(Long id);
    Optional<Long> saveAuthor(Author author);
    Optional<Author> updateAuthor(Author author);
    void deleteAuthor(Long id);
}
