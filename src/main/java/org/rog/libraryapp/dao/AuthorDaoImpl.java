package org.rog.libraryapp.dao;

import org.rog.libraryapp.entity.Author;
import org.rog.libraryapp.entity.Book;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AuthorDaoImpl implements AuthorDao {

    private static final String FIND_ALL_AUTHORS = "select * from authors";
    private static final String FIND_AUTHOR_BY_ID = "select * from authors where id = ?";
    private static final String SAVE_AUTHOR = "insert into authors (first_name, last_name, middle_name, birth_date, death_date) values (?, ?, ?, ?, ?)";
    private static final String UPDATE_AUTHOR = "update authors set first_name = ?, last_name = ?, middle_name = ?, birth_date = ?, death_date = ? where id = ?";
    private static final String DELETE_AUTHOR = "delete from authors where id = ?";
    private static final String FIND_AUTHOR_BOOKS = "select * from books where author_id = ?";

    private final BookDao bookDao;
    private final DataSource dataSource;

    public AuthorDaoImpl(DataSource dataSource, BookDao bookDao) {
        this.dataSource = dataSource;
        this.bookDao = bookDao;
    }

    @Override
    public List<Author> findAllAuthors() {
        List<Author> authors = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_AUTHORS)){
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                authors.add(fillAuthor(resultSet));
            }
            return authors;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Author> findAuthorById(Long id) { //Long
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_AUTHOR_BY_ID)){
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return Optional.of(fillAuthor(resultSet));
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
            throw  new RuntimeException(e);
        }
    }

    @Override
    public Optional<Author> updateAuthor(Author author) {
        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_AUTHOR)){
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
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_AUTHOR)){
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Author fillAuthor(ResultSet resultSet) throws SQLException {
        Author author = new Author();
        Long authorId = resultSet.getLong("id");
        author.setId(authorId);
        author.setFirstName(resultSet.getString("first_name"));
        author.setLastName(resultSet.getString("last_name"));
        author.setMiddleName(resultSet.getString("middle_name"));
        author.setBirthDate(resultSet.getDate("birth_date").toLocalDate());
        author.setDeathDate(resultSet.getDate("death_date").toLocalDate());

        author.setBooks(bookDao.findAllBooksByAuthor(authorId));
        return author;
    }
}
