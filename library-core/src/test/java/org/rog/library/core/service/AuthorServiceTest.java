package org.rog.library.core.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.rog.library.core.entity.Author;
import org.rog.library.core.exception.AuthorNotFoundException;
import org.rog.library.core.util.TestDataProvider;
import org.rog.library.core.entity.Book;
import org.rog.library.core.repository.AuthorRepository;
import org.rog.library.core.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
class AuthorServiceTest {
    private final AuthorService authorService;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    //private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public AuthorServiceTest(AuthorService authorService, AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorService = authorService;
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @BeforeEach
    void cleanTable() {
        bookRepository.deleteAll();
        authorRepository.deleteAll();
    }

    @Test
    //@SneakyThrows
    void saveAuthorAndFindByIdTest() {

        Author author = TestDataProvider.buildAuthor(1).get(0);
        authorService.saveAuthor(author);
        List<Book> books = TestDataProvider.buildBook(2, author);

        bookRepository.saveAll(books);
        Author foundAuthor = authorService.findAuthorById(author.getId());

        //assertEquals(objectMapper.writeValueAsString(author), objectMapper.writeValueAsString(foundAuthor));
        assertEquals(author.getFirstName(), foundAuthor.getFirstName());
        assertEquals(books.size(), foundAuthor.getBooks().size());
    }

    @Test
    void saveAuthorsAndFindAllTest(){
        List<Author> authors = TestDataProvider.buildAuthor(2);
        authorRepository.saveAll(authors);
        List<Book> books1 = TestDataProvider.buildBook(2, authors.get(0));
        List<Book> books2 = TestDataProvider.buildBook(3, authors.get(1));

        bookRepository.saveAll(books1);
        bookRepository.saveAll(books2);
        Author foundAuthor1 = authorService.findAuthorById(authors.get(0).getId());
        Author foundAuthor2 = authorService.findAuthorById(authors.get(1).getId());

        assertEquals(authors.size(), authorRepository.count());
        assertEquals(books1.size(), foundAuthor1.getBooks().size());
        assertEquals(books2.size(), foundAuthor2.getBooks().size());
    }

    @Test
    void updateAuthorTest(){
        String nameForUpdate = "NameForUpdate";
        Author author = TestDataProvider.buildAuthor(1).get(0);
        authorService.saveAuthor(author);

        author.setFirstName(nameForUpdate);
        authorService.updateAuthor(author);
        Author foundAuthor = authorService.findAuthorById(author.getId());

        assertEquals(nameForUpdate, foundAuthor.getFirstName());
    }

    @Test
    void deleteAuthorTest(){
        Author author = TestDataProvider.buildAuthor(1).get(0);
        authorService.saveAuthor(author);
        Long authorId = author.getId();
        List<Book> books = TestDataProvider.buildBook(2, author);
        bookRepository.saveAll(books);

        authorService.deleteAuthor(authorId);

        Assertions.assertThrows(AuthorNotFoundException.class, () -> authorService.findAuthorById(authorId));
        assertEquals(0, bookRepository.findBooksByAuthor(authorId).size());
    }
}

