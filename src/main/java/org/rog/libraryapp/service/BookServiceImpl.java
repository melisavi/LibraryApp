package org.rog.libraryapp.service;

import lombok.RequiredArgsConstructor;
import org.rog.libraryapp.entity.Author;
import org.rog.libraryapp.entity.Book;
import org.rog.libraryapp.exception.AuthorNotFoundException;
import org.rog.libraryapp.exception.BookNotFoundException;
import org.rog.libraryapp.repository.AuthorRepository;
import org.rog.libraryapp.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Override
    public List<Book> findAllBooks() {
        return bookRepository.findAllBooks();
    }

    @Override
    public Book findBookById(Long id) {
        if(!bookRepository.existsById(id)){
            throw new BookNotFoundException(id);
        }
        return bookRepository.findBookById(id).orElseThrow();
    }

    @Override
    public List<Book> findBooksByAuthor(Long authorId) {
        if(!authorRepository.existsById(authorId)){
            throw new AuthorNotFoundException(authorId);
        }
        return bookRepository.findBookByAuthor(authorId);
    }

    @Override
    public Book saveBook(Book book, Long authorId) {
        if(!authorRepository.existsById(authorId)){
            throw new AuthorNotFoundException(authorId);
        }
        book.setAuthor(Author.builder()
                .id(authorId)
                .build());
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Book book, Long id, Long authorId) {
        if(!authorRepository.existsById(authorId)){
            throw new AuthorNotFoundException(authorId);
        }
        book.setAuthor(Author.builder()
                .id(authorId)
                .build());
        if(!bookRepository.existsById(id)){
            throw new BookNotFoundException(id);
        }
        book.setId(id);
        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(Long id) {
        if(!bookRepository.existsById(id)){
            throw new BookNotFoundException(id);
        }
        bookRepository.deleteById(id);
    }
}
