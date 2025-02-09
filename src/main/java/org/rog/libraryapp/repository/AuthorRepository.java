package org.rog.libraryapp.repository;

import org.rog.libraryapp.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query(value = "select author from Author author left join fetch author.books")
    List<Author> findAllWithBooks();

    @Query(value = "select author from Author author left join fetch author.books where author.id =:id")
    Optional<Author> findByIdWithBooks(Long id);

    @Query(value = """
            select exists (
            select 1 from authors where first_name = :firstName and last_name = :lastName and middle_name = :middleName
            )
            """, nativeQuery = true)
    boolean existsByUniqueIndex(String firstName, String lastName, String middleName);
}
