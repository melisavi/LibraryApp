package org.rog.library.core.controller;

import lombok.RequiredArgsConstructor;
import org.rog.library.core.entity.Author;
import org.rog.library.core.service.AuthorService;
import org.rog.library.core.dto.AuthorDtoWithBooks;
import org.rog.library.core.mapper.AuthorMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class AuthorController {
    private final AuthorService authorService;
    private final AuthorMapper authorMapper;

    @GetMapping("/authors")
    public List<AuthorDtoWithBooks> getAllAuthors(){
        return authorService.findAllAuthors().stream().map(a -> authorMapper.toDtoWithBooks(a)).collect(Collectors.toList());
    }

    @GetMapping("/authors/{id}")
    public AuthorDtoWithBooks getAuthorById(@PathVariable(name = "id") Long id){
        return authorMapper.toDtoWithBooks(authorService.findAuthorById(id));
    }

    @PostMapping("/authors")
    public AuthorDtoWithBooks saveAuthor(@RequestBody AuthorDtoWithBooks authorDtoWithBooks){
        Author author = authorMapper.toEntityWithBooks(authorDtoWithBooks);
        return authorMapper.toDtoWithBooks(authorService.saveAuthor(author));
    }

    @PatchMapping("/authors/{id}")
    public AuthorDtoWithBooks updateAuthor(@PathVariable(name = "id") Long id,
                                           @RequestBody Author author){
        author.setId(id);
        return authorMapper.toDtoWithBooks(authorService.updateAuthor(author));
    }

    @DeleteMapping("/authors/{id}")
    public void deleteAuthor(@PathVariable(name = "id") Long id){
        authorService.deleteAuthor(id);
    }

}
