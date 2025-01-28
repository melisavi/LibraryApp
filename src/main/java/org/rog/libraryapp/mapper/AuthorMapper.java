package org.rog.libraryapp.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.rog.libraryapp.dto.AuthorDto;
import org.rog.libraryapp.dto.BookDto;
import org.rog.libraryapp.entity.Author;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class AuthorMapper {
    private final ModelMapper modelMapper;

    public AuthorDto toDto(Author author){
        return modelMapper.map(author, AuthorDto.class);
    }


}
