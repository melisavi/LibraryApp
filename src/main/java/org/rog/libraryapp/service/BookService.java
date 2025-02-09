package org.rog.libraryapp.service;

import org.rog.libraryapp.entity.Book;

import java.util.List;

public interface BookService {
    List<Book> findAllBooks();

    Book findBookById(Long id);

    List<Book> findBooksByAuthor(Long authorId);

    Book saveBook(Book book, Long authorId);

    Book updateBook(Book book, Long id, Long authorId);

    void deleteBook(Long id);
}
