package org.rog.libraryapp.service;

import org.rog.libraryapp.entity.Author;
import org.rog.libraryapp.entity.Book;

import java.util.List;

public interface LibraryService {
    List<Author> findAllAuthors();
    List<Book> findAllBooks();
    List<Book> findAllBooksByAuthor(int authorId);
    Author findAuthorById(int id);
    Book findBookById(int id);
    Integer saveAuthor(Author author);
    Integer saveBook(Book book);
    Author updateAuthor(Author author);
    Book updateBook(Book book);
    void deleteAuthor(int id);
    void deleteBook(int id);
}
