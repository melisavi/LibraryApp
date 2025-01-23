package org.rog.libraryapp.controller;

import org.rog.libraryapp.entity.Author;
import org.rog.libraryapp.entity.Book;
import org.rog.libraryapp.service.LibraryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LibraryController {
    private final LibraryService libraryService;

    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping("/authors")
    public List<Author> getAllAuthors(){
        return libraryService.findAllAuthors();
    }

    @GetMapping("/books")
    public List<Book> getAllBooks(){
        return libraryService.findAllBooks();
    }

    @GetMapping("/books/authors/{authorId}")
    public List<Book> getAllBooksByAuthor(@PathVariable(name = "authorId") int authorId){
        return libraryService.findAllBooksByAuthor(authorId);
    }

    @GetMapping("/authors/{id}")
    public Author getAuthorById(@PathVariable(name = "id") int id){
        return libraryService.findAuthorById(id);
    }

    @GetMapping("/books/{id}")
    public Book getBookById(@PathVariable(name = "id") int id){
        return libraryService.findBookById(id);
    }

    @PostMapping("/authors")
    public Author saveAuthor(@RequestBody Author author){
        Integer id = libraryService.saveAuthor(author);
        return libraryService.findAuthorById(id);
    }

    @PostMapping("/books")
    public Book saveBook(@RequestBody Book book){
        Integer id = libraryService.saveBook(book);
        return libraryService.findBookById(id);
    }

    @PatchMapping("/authors/{id}")
    public Author updateAuthor(@PathVariable(name = "id") int id,
                               @RequestBody Author author){
        author.setId(id);
        libraryService.updateAuthor(author);
        return libraryService.findAuthorById(id);
    }

    @PatchMapping("/books/{id}")
    public Book updateBook(@PathVariable(name = "id") int id,
                               @RequestBody Book book){
        book.setId(id);
        libraryService.updateBook(book);
        return libraryService.findBookById(id);
    }

    @DeleteMapping("/authors/{id}")
    public void deleteAuthor(@PathVariable(name = "id") int id){
        libraryService.deleteAuthor(id);
    }

    @DeleteMapping("/books/{id}")
    public void deleteBook(@PathVariable(name = "id") int id){
        libraryService.deleteBook(id);
    }
}
