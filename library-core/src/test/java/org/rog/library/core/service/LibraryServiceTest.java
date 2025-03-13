package org.rog.library.core.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.rog.library.core.entity.Author;
import org.rog.library.core.repository.AuthorRepository;
import org.rog.library.core.entity.Book;
import org.rog.library.core.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class LibraryServiceTest {
    private final AuthorService authorService;
    private final AuthorRepository authorRepository;
    private final BookService bookService;
    private final BookRepository bookRepository;

    @Autowired
    public LibraryServiceTest(AuthorService authorService, AuthorRepository authorRepository, BookService bookService, BookRepository bookRepository) {
        this.authorService = authorService;
        this.authorRepository = authorRepository;
        this.bookService = bookService;
        this.bookRepository = bookRepository;
    }

    @BeforeEach
    void cleanTable(){
        bookRepository.deleteAll();
        authorRepository.deleteAll();
    }

    @Test
    void saveAuthorByIdTest(){
        List<Book> books = new ArrayList<>();
        Author author = new Author(11L, "Лев", "Толстой", "Николаевич", LocalDate.of(1828, 1, 1), LocalDate.of(1910, 1, 1), books);
        author.getBooks().add(new Book(11L, "Воскресение", author));
        author.getBooks().add(new Book(12L, "Анна Каренина", author));
        authorService.saveAuthor(author);

        Author foundAuthor = authorService.findAuthorById(author.getId());
        assertEquals(author.getId(), foundAuthor.getId());
        assertEquals(author.getFirstName(), foundAuthor.getFirstName());
    }

}
