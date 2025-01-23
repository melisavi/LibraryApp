package org.rog.libraryapp.dao;

import org.rog.libraryapp.entity.Author;
import org.rog.libraryapp.entity.Book;

import java.util.List;
import java.util.Optional;

public interface LibraryDao {
    List<Author> findAllAuthors();
    List<Book> findAllBooks();
    List<Book> findAllBooksByAuthor(int authorId);
    Optional<Author> findAuthorById(int id);
    Optional<Book> findBookById(int id);
    Optional<Integer> saveAuthor(Author author);
    Optional<Integer> saveBook(Book book);
    Optional<Author> updateAuthor(Author author);
    Optional<Book> updateBook(Book book);
    void deleteAuthor(int id);
    void deleteBook(int id);
}
