package my.library.dao;

import my.library.entity.Author;
import my.library.entity.Book;
import my.library.entity.Genre;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookImplDao extends BaseDao<Book> {

    private static final String FIND_BY_ID = "select * from book  where id_book = ?";
    private static final String INSERT = "insert into book values(id_book,?,?,?,?,?,?)";
    private static final String UPDATE = "update book set name = ?,year = ?,isbn = ?,description = ?,id_genre = ?,id_author = ? where id_book = ?";
    private static final String DELETE = "delete from book  where id_book = ?";
    private static final String COUNT_BOOK_BY_GENRE = "select count(*) from book  where id_genre = ?";
    private static final String LIMIT_BOOK_BY_GENRE = "select * from book  where id_genre = ? limit ?,?";
    private static final String FIND_BY_NAME = "select * from book  where name = ?";

    private static final String ALLBOOKS = "select book.id_book, book.name , book.year ,book.isbn, book.description from book";

    private static final String FIND_BY_AUTHOR = "select * from book  where id_author = ?";


    public List<Book> getBooksByAuthor(List<Author> authors)throws Exception{
        List<Book> books = new ArrayList<>();
        Book book = null;
        try {
            for(Author author:authors) {
                try (PreparedStatement statement = getConnection().prepareStatement(FIND_BY_AUTHOR)) {
                    statement.setInt(1,author.getId());
                    ResultSet resultSet = statement.executeQuery();
                    while (resultSet.next()){
                    book = itemBook(resultSet);
                        books.add(book);
                    }

                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return books;
    }


    public List<Book> getAllBooks(){
        List<Book> list = new ArrayList<>();
        Book book;
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(ALLBOOKS)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        book = itemBook(resultSet);
                        list.add(book);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Book insert(Book item) throws Exception {
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS)) {
                statementBook(statement, item);
                statement.executeUpdate();
                try (ResultSet resultSet = statement.getGeneratedKeys()) {
                    resultSet.next();
                    item.setId(resultSet.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new Exception("can't insert " + this.getClass().getSimpleName() + "/" + item, e);
        }
        return item;
    }

    @Override
    public Book findById(int id) throws Exception {
        Book book = null;
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(FIND_BY_ID)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        book = itemBook(resultSet);
                    }
                }
            }
        } catch (SQLException e) {
            throw new Exception("can't find by id" + this.getClass().getSimpleName(), e);
        }
        return book;
    }

    @Override
    public void update(Book item) throws Exception {
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(UPDATE)) {
                statementBook(statement, item);
                statement.setInt(7, item.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new Exception("can't update " + this.getClass().getSimpleName() + "/" + item, e);
        }
    }

    @Override
    public void delete(Book item) throws Exception {
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(DELETE)) {
                statement.setInt(1, item.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new Exception("can't delete book " + this.getClass().getSimpleName() + "/" + item, e);
        }
    }

    public int getBookCountByGenre(Genre genre) throws Exception {
        int count = 0;
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(COUNT_BOOK_BY_GENRE)) {
                statement.setInt(1, genre.getId());
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        count = resultSet.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            throw new Exception("can't count by genre " + this.getClass().getSimpleName(), e);
        }
        return count;
    }

    public List<Book> getLimitBookByGenre(Genre genre, int start, int count) throws Exception {
        List<Book> list = new ArrayList<>();
        Book book = null;
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(LIMIT_BOOK_BY_GENRE)) {
                statement.setInt(1, genre.getId());
                statement.setInt(2, ((start - 1) * count));
                statement.setInt(3, count);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        book = itemBook(resultSet);
                        list.add(book);
                    }
                }
            }
        } catch (SQLException e) {
            throw new Exception("can't get list of book by genre " + this.getClass().getSimpleName(), e);
        }
        return list;
    }

    public List<Book> findByName(String name) throws Exception {
        List<Book> list = new ArrayList<>();
        Book book = null;
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(FIND_BY_NAME)) {
                statement.setString(1, name);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        book = itemBook(resultSet);
                        list.add(book);
                    }
                }
            }
        } catch (SQLException e) {
            throw new Exception("can't find by name " + this.getClass().getSimpleName(), e);
        }
        return list;
    }

    private Book itemBook(ResultSet resultSet) throws SQLException {
        Book book = new Book();
        book.setId(resultSet.getInt(1));
        book.setName(resultSet.getString(2));
        book.setDate(resultSet.getDate(3));
        book.setIsbn(resultSet.getString(4));
        book.setDescription(resultSet.getString(5));
        return book;
    }

    private PreparedStatement statementBook(PreparedStatement statement, Book item) throws SQLException {
        statement.setString(1, item.getName());
        statement.setDate(2, item.getDate());
        statement.setString(3, item.getIsbn());
        statement.setString(4, item.getDescription());
        statement.setInt(5, item.getGenre().getId());
        statement.setInt(6, item.getAuthor().getId());
        return statement;
    }

}

