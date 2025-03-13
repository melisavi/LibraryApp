package org.rog.library.core.service;

import org.rog.library.core.entity.Book;

import java.util.List;

public interface BookService {
    List<Book> findAllBooks();

    Book findBookById(Long id);

    List<Book> findBooksByAuthor(Long authorId);

    Book saveBook(Book book, Long authorId);

    Book updateBook(String title, Long id);

    void deleteBook(Long id);

    Book updateAuthorInBook(Book book, Long authorId);
}
