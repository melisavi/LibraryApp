package org.rog.libraryapp.service;

import org.rog.libraryapp.dao.AuthorDao;
import org.rog.libraryapp.entity.Author;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorDao authorDao;

    public AuthorServiceImpl(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @Override
    public List<Author> findAllAuthors() {
        return authorDao.findAllAuthors();
    }

    @Override
    public Author findAuthorById(Long id) {
        return authorDao.findAuthorById(id).orElseThrow();
    }

    @Override
    public Long saveAuthor(Author author) {
        return authorDao.saveAuthor(author).orElseThrow();
    }

    @Override
    public Author updateAuthor(Author author) {
        return authorDao.updateAuthor(author).orElseThrow();
    }

    @Override
    public void deleteAuthor(Long id) {
        authorDao.deleteAuthor(id);
    }

}
