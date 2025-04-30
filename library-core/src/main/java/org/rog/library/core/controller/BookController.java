package org.rog.library.core.controller;

import lombok.AllArgsConstructor;
import org.rog.library.core.dto.BookWithoutAuthorDto;
import org.rog.library.core.mapper.BookMapper;
import org.rog.library.core.service.BookService;
import org.rog.library.core.dto.BookWithAuthorDto;
import org.rog.library.core.entity.Book;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @PatchMapping("/books/{id}")
    BookWithoutAuthorDto updateBook(@PathVariable(name = "id") Long id,
                                    @RequestBody BookWithoutAuthorDto bookWithoutAuthorDto) {
        return bookMapper.toDtoWithoutAuthor(bookService.updateBook(bookWithoutAuthorDto.getTitle(), id));
    }

    @PatchMapping("/author/{authorId}/books/{id}")
    BookWithAuthorDto updateAuthorInBook(@PathVariable(name = "authorId") Long authorId,
                                 @PathVariable(name = "id") Long id
                                 ) {
        Book book = bookService.findBookById(id);
        return bookMapper.toDtoWithAuthor(bookService.updateAuthorInBook(book, authorId));
    }

    @DeleteMapping("/books/{id}")
    void deleteBook(@PathVariable(name = "id") Long id) {
        bookService.deleteBook(id);
    }

}
