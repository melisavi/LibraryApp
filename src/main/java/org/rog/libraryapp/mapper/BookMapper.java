package org.rog.libraryapp.mapper;

import org.mapstruct.Mapper;
import org.rog.libraryapp.dto.BookWithAuthorDto;
import org.rog.libraryapp.dto.BookWithoutAuthorDto;
import org.rog.libraryapp.entity.Book;

@Mapper
public interface BookMapper {

    BookWithoutAuthorDto toDtoWithoutAuthor(Book book);
    Book toEntityWithoutAuthor(BookWithoutAuthorDto bookWithoutAuthorDto);

    BookWithAuthorDto toDtoWithAuthor(Book book);
    Book toEntityWithAuthor(BookWithAuthorDto bookWithAuthorDto);
}
