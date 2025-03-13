package org.rog.library.core.mapper;

import org.mapstruct.Mapper;
import org.rog.library.core.dto.AuthorDtoWithoutBooks;
import org.rog.library.core.entity.Author;
import org.rog.library.core.dto.AuthorDtoWithBooks;

@Mapper
public interface AuthorMapper {

    AuthorDtoWithBooks toDtoWithBooks(Author author);
    Author toEntityWithBooks(AuthorDtoWithBooks authorDtoWithBooks);
    AuthorDtoWithoutBooks toDtoWithoutBooks(Author author);
    Author toEntityWithoutBooks(AuthorDtoWithoutBooks authorDtoWithoutBooks);
}
