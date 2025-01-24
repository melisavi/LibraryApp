package org.rog.libraryapp.controller;

import org.rog.libraryapp.entity.Author;
import org.rog.libraryapp.service.AuthorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/authors")
    public List<Author> getAllAuthors(){
        return authorService.findAllAuthors();
    }

    @GetMapping("/authors/{id}")
    public Author getAuthorById(@PathVariable(name = "id") Long id){
        return authorService.findAuthorById(id);
    }

    @PostMapping("/authors")
    public Author saveAuthor(@RequestBody Author author){
        Long id = authorService.saveAuthor(author);
        return authorService.findAuthorById(id);
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
