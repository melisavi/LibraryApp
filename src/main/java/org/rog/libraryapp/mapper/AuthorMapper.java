package org.rog.libraryapp.mapper;

import org.rog.libraryapp.dto.AuthorDto;
import org.rog.libraryapp.dto.BookDto;
import org.rog.libraryapp.entity.Author;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class AuthorMapper {
    public AuthorDto toDto(Author author){
        AuthorDto authorDto = new AuthorDto();
        authorDto.setId(author.getId());
        authorDto.setFirstName(author.getFirstName());
        authorDto.setLastName(author.getLastName());
        authorDto.setMiddleName(author.getMiddleName());
        authorDto.setBirthDate(author.getBirthDate());
        authorDto.setDeathDate(author.getDeathDate());
        authorDto.setBooks(author.getBooks().stream().map(b -> new BookDto(b.getId(), b.getTitle())).collect(Collectors.toList()));
        return authorDto;
    }
}
