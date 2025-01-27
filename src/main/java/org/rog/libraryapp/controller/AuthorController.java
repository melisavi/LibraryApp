package org.rog.libraryapp.controller;

import org.rog.libraryapp.dto.AuthorDto;
import org.rog.libraryapp.entity.Author;
import org.rog.libraryapp.mapper.AuthorMapper;
import org.rog.libraryapp.service.AuthorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AuthorController {
    private final AuthorService authorService;
    private final AuthorMapper authorMapper;

    public AuthorController(AuthorService authorService, AuthorMapper authorMapper) {
        this.authorService = authorService;
        this.authorMapper = authorMapper;
    }

    @GetMapping("/authors")
    public List<AuthorDto> getAllAuthors(){
        //return authorService.findAllAuthors();
        //AuthorMapper authorMapper = new AuthorMapper();
        return authorService.findAllAuthors().stream().map(a -> authorMapper.toDto(a)).collect(Collectors.toList());
    }

    @GetMapping("/authors/{id}")
    public AuthorDto getAuthorById(@PathVariable(name = "id") Long id){
        //AuthorMapper authorMapper = new AuthorMapper();
        Author a = authorService.findAuthorById(id);
        return authorMapper.toDto(a);
    }

    @PostMapping("/authors")
    public Author saveAuthor(@RequestBody Author author){
        return authorService.saveAuthor(author);
    }

    @PatchMapping("/authors/{id}")
    public Author updateAuthor(@PathVariable(name = "id") Long id,
                               @RequestBody Author author){
        author.setId(id);
        authorService.updateAuthor(author);
        return authorService.findAuthorById(id);
    }

    @DeleteMapping("/authors/{id}")
    public void deleteAuthor(@PathVariable(name = "id") Long id){
        authorService.deleteAuthor(id);
    }

}
