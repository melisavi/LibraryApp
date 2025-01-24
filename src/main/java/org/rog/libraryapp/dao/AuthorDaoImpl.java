package org.rog.libraryapp.dao;

import org.rog.libraryapp.entity.Author;
import org.rog.libraryapp.entity.Book;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.sql.Date;
import java.util.*;

@Repository
public class AuthorDaoImpl implements AuthorDao {

    private static final String FIND_ALL_AUTHORS = "select a.id as author_id, a.first_name, a.last_name, a.middle_name, a.birth_date, a.death_date, " +
            "b.id as book_id, b.title " +
            "from books as b join authors a on b.author_id = a.id";
    private static final String FIND_AUTHOR_BY_ID = FIND_ALL_AUTHORS + " where a.id = ?";
    private static final String SAVE_AUTHOR = "insert into authors (first_name, last_name, middle_name, birth_date, death_date) values (?, ?, ?, ?, ?)";
    private static final String UPDATE_AUTHOR = "update authors set first_name = ?, last_name = ?, middle_name = ?, birth_date = ?, death_date = ? where id = ?";
    private static final String DELETE_AUTHOR = "delete from authors where id = ?";

    private final DataSource dataSource;

    public AuthorDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Author> findAllAuthors() {
        Map<Long, Author> authors = new HashMap<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_AUTHORS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long authorId = resultSet.getLong("author_id");
                Book book = fillBook(resultSet);
                if (authors.containsKey(authorId)) {
                    authors.get(authorId).getBooks().add(book);
                } else {
                    Author author = fillAuthor(resultSet);
                    author.getBooks().add(book);
                    authors.put(authorId, author);
                }
            }
            return new ArrayList<>(authors.values());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Author> findAuthorById(Long id) { //Long
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_AUTHOR_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Author author = fillAuthor(resultSet);
                do {
                    author.getBooks().add(fillBook(resultSet));
                } while (resultSet.next());
                return Optional.of(author);
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Long> saveAuthor(Author author) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_AUTHOR, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, author.getFirstName());
            preparedStatement.setString(2, author.getLastName());
            preparedStatement.setString(3, author.getMiddleName());
            preparedStatement.setDate(4, new java.sql.Date(Date.valueOf(author.getBirthDate()).getTime()));
            preparedStatement.setDate(5, new java.sql.Date(Date.valueOf(author.getDeathDate()).getTime()));
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs != null && rs.next()) {
                author.setId(rs.getLong(1));
            }
            return Optional.of(author.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Author> updateAuthor(Author author) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_AUTHOR)) {
            preparedStatement.setString(1, author.getFirstName());
            preparedStatement.setString(2, author.getLastName());
            preparedStatement.setString(3, author.getMiddleName());
            preparedStatement.setDate(4, new java.sql.Date(Date.valueOf(author.getBirthDate()).getTime()));
            preparedStatement.setDate(5, new java.sql.Date(Date.valueOf(author.getDeathDate()).getTime()));
            preparedStatement.setLong(6, author.getId());
            preparedStatement.executeUpdate();
            return Optional.of(author);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteAuthor(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_AUTHOR)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Author fillAuthor(ResultSet resultSet) throws SQLException {
        Author author = new Author();
        Long authorId = resultSet.getLong("author_id");
        author.setId(authorId);
        author.setFirstName(resultSet.getString("first_name"));
        author.setLastName(resultSet.getString("last_name"));
        author.setMiddleName(resultSet.getString("middle_name"));
        author.setBirthDate(resultSet.getDate("birth_date").toLocalDate());
        author.setDeathDate(resultSet.getDate("death_date").toLocalDate());

        author.setBooks(new ArrayList<>());
        return author;
    }

    private Book fillBook(ResultSet resultSet) throws SQLException {
        Book book = new Book(resultSet.getLong("book_id"),
                resultSet.getString("title"));
        return book;
    }
}
