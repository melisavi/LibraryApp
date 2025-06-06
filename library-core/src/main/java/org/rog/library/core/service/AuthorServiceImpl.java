package org.rog.library.core.service;

import org.rog.library.core.entity.Author;
import org.rog.library.core.exception.AuthorAlreadyExistsException;
import org.rog.library.core.exception.AuthorNotFoundException;
import org.rog.library.core.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> findAllAuthors() {
        return authorRepository.findAllWithBooks();
    }

    @Override
    public Author findAuthorById(Long id) {
        if (!authorRepository.existsById(id)){
            throw new AuthorNotFoundException(id);
        }
        return authorRepository.findByIdWithBooks(id).orElseThrow();
    }

    @Override
    public Author saveAuthor(Author author) {
        if (authorRepository.existsByUniqueIndex(author.getFirstName(), author.getLastName(), author.getMiddleName())) {
            throw new AuthorAlreadyExistsException(author.getFirstName(), author.getLastName(), author.getMiddleName());
        }
        return authorRepository.save(author);
    }

    @Override
    public Author updateAuthor(Author author) {
        if (!authorRepository.existsById(author.getId())) {
            throw new AuthorNotFoundException(author.getId());
        }
        if (authorRepository.existsByUniqueIndex(author.getFirstName(), author.getLastName(), author.getMiddleName())) {
            throw new AuthorAlreadyExistsException(author.getFirstName(), author.getLastName(), author.getMiddleName());
        }
        return authorRepository.save(author);
    }

    @Override
    public void deleteAuthor(Long id) {
        if (!authorRepository.existsById(id)){
            throw new AuthorNotFoundException(id);
        }
        authorRepository.deleteById(id);
    }

}
