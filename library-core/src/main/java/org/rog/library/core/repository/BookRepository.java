package org.rog.library.core.repository;

import org.rog.library.core.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Query(value = "select book from Book book")
    List<Book> findAllBooks();

    @Query(value = "select book from Book book where book.id = :id")
    Optional<Book> findBookById(Long id);

    @Query(value = "select book from Book book where book.author = (select author from Author author where author.id = :authorId)")
    List<Book> findBooksByAuthor(Long authorId);
}
