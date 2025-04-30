package org.rog.library.core.repository;

import org.rog.library.core.entity.ApplicationUser;
import org.rog.library.core.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, String> {
    @Query(value = "select user from ApplicationUser user where user.id =:id")
    Optional<ApplicationUser> findById(long id);

    @Query(value = "select book from Book book join book.applicationUser where book.applicationUser.login = :login")
    List<Book> findAllBooksByUser(String login);

    @Query(value = "select exists (select 1 from books b where b.id = :bookId and b.login is null)", nativeQuery = true)
    boolean checkBookAvailability(Long bookId);

    @Query(value = "select exists (select 1 from books b where b.id = :bookId and b.login is not null)", nativeQuery = true)
    boolean isBookTaken(Long bookId);

    @Modifying
    @Query(value = "update Book b set b.applicationUser.login = :login where b.id = :bookId and b.applicationUser is null")
    void takeBookOn(Long bookId, String login);

    @Modifying
    @Query(value = "update Book b set b.applicationUser = null where b.id = :bookId and b.applicationUser.login = :login")
    void takeBookOff(Long bookId, String login);
}
