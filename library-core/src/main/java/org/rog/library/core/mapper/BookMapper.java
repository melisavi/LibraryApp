package org.rog.library.core.mapper;

import org.mapstruct.Mapper;
import org.rog.library.core.dto.BookWithoutAuthorDto;
import org.rog.library.core.dto.BookWithAuthorDto;
import org.rog.library.core.entity.Book;

@Mapper
public interface BookMapper {

    BookWithoutAuthorDto toDtoWithoutAuthor(Book book);
    Book toEntityWithoutAuthor(BookWithoutAuthorDto bookWithoutAuthorDto);

    BookWithAuthorDto toDtoWithAuthor(Book book);
    Book toEntityWithAuthor(BookWithAuthorDto bookWithAuthorDto);
}
