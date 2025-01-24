package org.rog.libraryapp.dao;

import org.rog.libraryapp.entity.Book;

import java.util.List;

public interface BookDao {
    List<Book> findAllBooksByAuthor(Long authorId);
}
