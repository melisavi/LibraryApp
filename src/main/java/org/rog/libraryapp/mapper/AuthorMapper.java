package org.rog.libraryapp.mapper;

import org.mapstruct.Mapper;
import org.rog.libraryapp.dto.AuthorDto;
import org.rog.libraryapp.entity.Author;

@Mapper
public interface AuthorMapper {

    AuthorDto toDto(Author author);
}
