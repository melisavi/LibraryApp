package org.rog.libraryapp.dao;

import org.rog.libraryapp.entity.Book;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookDaoImpl implements BookDao {
    private static final String FIND_ALL_BOOKS_BY_AUTHOR = "select * from books where author_id = ?";
    private final DataSource dataSource;

    public BookDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Book> findAllBooksByAuthor(Long authorId) {
        List<Book> books = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_BOOKS_BY_AUTHOR)){
            preparedStatement.setLong(1, authorId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long bookId = resultSet.getLong("id");
                String bookTitle = resultSet.getString("title");
                Book book = new Book(bookId, bookTitle, authorId);
                books.add(book);
            }
            return books;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
