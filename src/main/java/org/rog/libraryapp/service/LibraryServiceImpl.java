package org.rog.libraryapp.service;

import org.rog.libraryapp.dao.LibraryDao;
import org.rog.libraryapp.entity.Author;
import org.rog.libraryapp.entity.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryServiceImpl implements LibraryService {
    private final LibraryDao libraryDao;

    public LibraryServiceImpl(LibraryDao libraryDao) {
        this.libraryDao = libraryDao;
    }

    @Override
    public List<Author> findAllAuthors() {
        return libraryDao.findAllAuthors();
    }

    @Override
    public List<Book> findAllBooks() {
        return libraryDao.findAllBooks();
    }

    @Override
    public List<Book> findAllBooksByAuthor(int authorId) {
        return libraryDao.findAllBooksByAuthor(authorId);
    }

    @Override
    public Author findAuthorById(int id) {
        return libraryDao.findAuthorById(id).orElseThrow();
    }

    @Override
    public Book findBookById(int id) {
        return libraryDao.findBookById(id).orElseThrow();
    }

    @Override
    public Integer saveAuthor(Author author) {
        return libraryDao.saveAuthor(author).orElseThrow();
    }

    @Override
    public Integer saveBook(Book book) {
        return libraryDao.saveBook(book).orElseThrow();
    }

    @Override
    public Author updateAuthor(Author author) {
        return libraryDao.updateAuthor(author).orElseThrow();
    }

    @Override
    public Book updateBook(Book book) {
        return libraryDao.updateBook(book).orElseThrow();
    }

    @Override
    public void deleteAuthor(int id) {
        libraryDao.deleteAuthor(id);
    }

    @Override
    public void deleteBook(int id) {
        libraryDao.deleteBook(id);
    }
}
