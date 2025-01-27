package org.rog.libraryapp.repository;

import org.rog.libraryapp.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query(value = "select author from Author author join fetch author.books")
    List<Author> findAllWithBooks();

    @Query(value = "select author from Author author join fetch author.books where author.id =:id")
    Optional<Author> findByIdWithBooks(Long id);
}
