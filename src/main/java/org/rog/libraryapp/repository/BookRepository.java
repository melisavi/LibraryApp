package org.rog.libraryapp.repository;

import org.rog.libraryapp.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Query(value = "select book from Book book")
    List<Book> findAllBooks();

    @Query(value = "select book from Book book where book.id = :id")
    Optional<Book> findBookById(Long id);

    @Query(value = "select book from Book book where book.author = (select author from Author author where author.id = :authorId)")
    List<Book> findBookByAuthor(Long authorId);
}
