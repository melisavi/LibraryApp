package org.rog.libraryapp.mapper;

import org.mapstruct.Mapper;
import org.rog.libraryapp.dto.AuthorDtoWithBooks;
import org.rog.libraryapp.dto.AuthorDtoWithoutBooks;
import org.rog.libraryapp.entity.Author;

@Mapper
public interface AuthorMapper {

    AuthorDtoWithBooks toDtoWithBooks(Author author);
    Author toEntityWithBooks(AuthorDtoWithBooks authorDtoWithBooks);
    AuthorDtoWithoutBooks toDtoWithoutBooks(Author author);
    Author toEntityWithoutBooks(AuthorDtoWithoutBooks authorDtoWithoutBooks);
}
