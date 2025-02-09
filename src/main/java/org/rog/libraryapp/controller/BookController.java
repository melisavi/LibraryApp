package org.rog.libraryapp.controller;

import lombok.AllArgsConstructor;
import org.rog.libraryapp.dto.BookWithAuthorDto;
import org.rog.libraryapp.entity.Book;
import org.rog.libraryapp.mapper.BookMapper;
import org.rog.libraryapp.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class BookController {
    private final BookService bookService;
    private final BookMapper bookMapper;

    @GetMapping("/books")
    List<BookWithAuthorDto> getAllBooks() {
        return bookService.findAllBooks().stream().map(book -> bookMapper.toDtoWithAuthor(book)).collect(Collectors.toList());
    }

    @GetMapping("/books/{id}")
    BookWithAuthorDto getBookById(@PathVariable(name = "id") Long id) {
        return bookMapper.toDtoWithAuthor(bookService.findBookById(id));
    }

    @GetMapping("/author/{authorId}/books")
    List<BookWithAuthorDto> getBooksByAuthor(@PathVariable(name = "authorId") Long authorId) {
        return bookService.findBooksByAuthor(authorId)
                .stream().map(book -> bookMapper.toDtoWithAuthor(book))
                .collect(Collectors.toList());
    }

    @PostMapping("/author/{authorId}/book")
    BookWithAuthorDto saveBook(@PathVariable(name = "authorId") Long authorId,
                                  @RequestBody BookWithAuthorDto bookWithAuthorDto) {
        return bookMapper.toDtoWithAuthor(
                bookService.saveBook(
                        bookMapper.toEntityWithAuthor(bookWithAuthorDto), authorId
                )
        );
    }

    @PatchMapping("/author/{authorId}/books/{id}")
    BookWithAuthorDto updateBook(@PathVariable(name = "authorId") Long authorId,
                                 @PathVariable(name = "id") Long id,
                                 @RequestBody BookWithAuthorDto bookWithAuthorDto) {
        return bookMapper.toDtoWithAuthor(bookService.updateBook(bookMapper.toEntityWithAuthor(bookWithAuthorDto), id, authorId));
    }

    @DeleteMapping("/books/{id}")
    void deleteBook(@PathVariable(name = "id") Long id) {
        bookService.deleteBook(id);
    }
}
