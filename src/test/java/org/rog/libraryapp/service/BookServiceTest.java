package org.rog.libraryapp.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.rog.libraryapp.entity.Author;
import org.rog.libraryapp.entity.Book;
import org.rog.libraryapp.exception.AuthorNotFoundException;
import org.rog.libraryapp.exception.BookNotFoundException;
import org.rog.libraryapp.repository.AuthorRepository;
import org.rog.libraryapp.repository.BookRepository;
import org.rog.libraryapp.util.TestDataProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
class BookServiceTest {
    private final AuthorService authorService;
    private final AuthorRepository authorRepository;
    private final BookService bookService;
    private final BookRepository bookRepository;
    //private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public BookServiceTest(AuthorService authorService, AuthorRepository authorRepository, BookService bookService, BookRepository bookRepository) {
        this.authorService = authorService;
        this.authorRepository = authorRepository;
        this.bookService = bookService;
        this.bookRepository = bookRepository;
    }

    @BeforeEach
    void cleanTable() {
        bookRepository.deleteAll();
        authorRepository.deleteAll();
    }

    @Test
    //@SneakyThrows
    void saveBookAndFindByIdTest() {
        Author author = TestDataProvider.buildAuthor(1).get(0);
        authorService.saveAuthor(author);
        Long authorId = author.getId();
        Book book = TestDataProvider.buildBook(1, author).get(0);

        bookService.saveBook(book, authorId);
        Book foundBook = bookService.findBookById(book.getId());

        assertEquals(authorId, foundBook.getAuthor().getId());
        assertEquals(book.getTitle(), foundBook.getTitle());
    }

    @Test
    void saveBooksAndFindAllTest(){
        Author author = TestDataProvider.buildAuthor(1).get(0);
        authorService.saveAuthor(author);
        List<Book> books = TestDataProvider.buildBook(2, author);

        bookRepository.saveAll(books);
        List<Book> foundBooks = bookService.findBooksByAuthor(author.getId());

        assertEquals(books.size(), foundBooks.size());
    }

    @Test
    void updateBookTest(){
        String titleForUpdate = "TitleForUpdate";
        Author author = TestDataProvider.buildAuthor(1).get(0);
        authorService.saveAuthor(author);
        Book book = TestDataProvider.buildBook(1, author).get(0);
        bookService.saveBook(book, author.getId());
        Long bookId = book.getId();

        bookService.updateBook(titleForUpdate, bookId);
        Book foundBook = bookService.findBookById(bookId);

        assertEquals(titleForUpdate, foundBook.getTitle());
    }

    @Test
    void updateAuthorInBookTest(){
        List<Author> authors = TestDataProvider.buildAuthor(2);
        authorRepository.saveAll(authors);
        Author authorBeforeUpdate = authors.get(0);
        Author authorAfterUpdate = authors.get(1);
        Book book = TestDataProvider.buildBook(1, authorBeforeUpdate).get(0);
        bookService.saveBook(book, authorBeforeUpdate.getId());

        bookService.updateAuthorInBook(book, authorAfterUpdate.getId());
        Book foundBook = bookService.findBookById(book.getId());

        assertEquals(authorAfterUpdate.getId(), foundBook.getAuthor().getId());
    }

    @Test
    void deleteBookTest(){
        Author author = TestDataProvider.buildAuthor(1).get(0);
        authorService.saveAuthor(author);
        Book book = TestDataProvider.buildBook(1, author).get(0);
        bookService.saveBook(book, author.getId());
        Long bookId = book.getId();

        bookService.deleteBook(bookId);

        Assertions.assertThrows(BookNotFoundException.class, () -> bookService.findBookById(bookId));
    }
}

