package org.rog.library.core.service;

import lombok.RequiredArgsConstructor;
import org.rog.library.core.entity.Author;
import org.rog.library.core.entity.Book;
import org.rog.library.core.exception.AuthorNotFoundException;
import org.rog.library.core.exception.BookNotFoundException;
import org.rog.library.core.repository.AuthorRepository;
import org.rog.library.core.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        return bookRepository.findBookById(id).orElseThrow(()-> new BookNotFoundException(id));
    }

    @Override
    public List<Book> findBooksByAuthor(Long authorId) {
        if(!authorRepository.existsById(authorId)){
            throw new AuthorNotFoundException(authorId);
        }
        return bookRepository.findBooksByAuthor(authorId);
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
    public Book updateBook(String title, Long id) {
        if(!bookRepository.existsById(id)){
            throw new BookNotFoundException(id);
        }
        Optional<Book> o = bookRepository.findBookById(id);
        if (!o.isEmpty()) {
            Book book = o.get();
            book.setTitle(title);
            return bookRepository.save(book);
        } else throw new BookNotFoundException(id);
    }

    @Override
    public Book updateAuthorInBook(Book book, Long authorId) {
        Long id = book.getId();
        if(!authorRepository.existsById(authorId)){
            throw new AuthorNotFoundException(authorId);
        }
        book.setAuthor(Author.builder()
                .id(authorId)
                .build());
        if(!bookRepository.existsById(id)){
            throw new BookNotFoundException(id);
        }
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
