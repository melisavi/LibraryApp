package org.rog.library.core.service;

import lombok.RequiredArgsConstructor;
import org.rog.library.core.entity.ApplicationUser;
import org.rog.library.core.entity.Book;
import org.rog.library.core.exception.BookUnavailableException;
import org.rog.library.core.repository.ApplicationUserRepository;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final ApplicationUserRepository repository;

    @Override
    public void save(ApplicationUser applicationUser) {
        repository.save(applicationUser);
    }

    //TODO: why two select queries
    @Override
    public List<Book> findAllBooksByUser(String login) {
        return repository.findAllBooksByUser(login);
    }

    /**
     * In this case is better to use optimistic lock instead of repeatable_read.
     */
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    @Override
    public void takeBookOn(Long bookId, String login) {
        if (repository.checkBookAvailability(bookId)) {
            repository.takeBookOn(bookId, login);
        } else {
            throw new BookUnavailableException(bookId);
        }
    }

    @Transactional
    @Override
    public void takeBookOff(Long bookId, String login) {
        if (repository.isBookTaken(bookId)) {
            repository.takeBookOff(bookId, login);
        } else {
            throw new RuntimeException();
        }
    }
}
