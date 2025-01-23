package org.rog.libraryapp.dao;

import org.rog.libraryapp.entity.Author;
import org.rog.libraryapp.entity.Book;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class LibraryDaoImpl implements LibraryDao {

    private static final String FIND_ALL_AUTHORS = "select * from authors";
    private static final String FIND_ALL_BOOKS = "select * from books";
    private static final String FIND_ALL_BOOKS_BY_AUTHOR = "select * from books where author_id = ?";
    private static final String FIND_AUTHOR_BY_ID = "select * from authors where id = ?";
    private static final String FIND_BOOK_BY_ID = "select * from books where id = ?";
    private static final String SAVE_AUTHOR = "insert into authors (name, birth_date) values (?, ?)";
    private static final String SAVE_BOOK = "insert into books (title, author_id) values (?, ?)";
    private static final String UPDATE_AUTHOR = "update authors set name = ?, birth_date = ? where id = ?";
    private static final String UPDATE_BOOK = "update books set title = ?, author_id = ? where id = ?";
    private static final String DELETE_AUTHOR = "delete from authors where id = ?";
    private static final String DELETE_BOOK = "delete from books where id = ?";
    private final DataSource dataSource;

    public LibraryDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Author> findAllAuthors() {
        List<Author> authors = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_AUTHORS)){
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int authorId = resultSet.getInt("id");
                String authorName = resultSet.getString("name");
                LocalDate authorBirthDate = resultSet.getDate("birth_date").toLocalDate();
                Author author = new Author(authorId, authorName, authorBirthDate);
                authors.add(author);
            }
            return authors;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Book> findAllBooks() {
        List<Book> books = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_BOOKS)){
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int bookId = resultSet.getInt("id");
                String bookTitle = resultSet.getString("title");
                int authorId = resultSet.getInt("author_id");
                Book book = new Book(bookId, bookTitle, authorId);
                books.add(book);
            }
            return books;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Book> findAllBooksByAuthor(int authorId) {
        List<Book> books = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_BOOKS_BY_AUTHOR)){
            preparedStatement.setInt(1, authorId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int bookId = resultSet.getInt("id");
                String bookTitle = resultSet.getString("title");
                Book book = new Book(bookId, bookTitle, authorId);
                books.add(book);
            }
            return books;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Author> findAuthorById(int id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_AUTHOR_BY_ID)){
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                int authorId = resultSet.getInt("id");
                String authorName = resultSet.getString("name");
                LocalDate authorBirthDate = resultSet.getDate("birth_date").toLocalDate();
                Author author = new Author(authorId, authorName, authorBirthDate);
                return Optional.of(author);
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Book> findBookById(int id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BOOK_BY_ID)){
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                int bookId = resultSet.getInt("id");
                String bookTitle = resultSet.getString("title");
                int authorId = resultSet.getInt("author_id");
                Book book = new Book(bookId, bookTitle, authorId);
                return Optional.of(book);
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Integer> saveAuthor(Author author) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_AUTHOR, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, author.getName());
            preparedStatement.setDate(2, new java.sql.Date(Date.valueOf(author.getBirthDate()).getTime()));
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs != null && rs.next()) {
                author.setId(rs.getInt(1));
            }
            return Optional.of(author.getId());
        } catch (SQLException e) {
            throw  new RuntimeException(e);
        }
    }

    @Override
    public Optional<Integer> saveBook(Book book) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_BOOK, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setInt(2, book.getAuthorId());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs != null && rs.next()) {
                book.setId(rs.getInt(1));
            }
            return Optional.of(book.getId());
        } catch (SQLException e) {
            throw  new RuntimeException(e);
        }
    }

    @Override
    public Optional<Author> updateAuthor(Author author) {
        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_AUTHOR)){
            preparedStatement.setString(1, author.getName());
            preparedStatement.setDate(2, new java.sql.Date(Date.valueOf(author.getBirthDate()).getTime()));
            preparedStatement.setInt(3, author.getId());
            preparedStatement.executeUpdate();
            return Optional.of(author);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Book> updateBook(Book book) {
        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BOOK)){
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setInt(2, book.getAuthorId());
            preparedStatement.setInt(3, book.getId());
            preparedStatement.executeUpdate();
            return Optional.of(book);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteAuthor(int id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_AUTHOR)){
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteBook(int id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BOOK)){
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
