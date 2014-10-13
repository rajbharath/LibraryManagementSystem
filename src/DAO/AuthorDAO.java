package DAO;

import main.Author;
import main.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ganeswari on 10/12/14.
 */
public class AuthorDAO extends AbstractDAO<Author> {
    @Override
    public long create(Author author) {
        return 0;
    }

    @Override
    public Author retrieve(long Id) {
        try {
            ResultSet resultSet = stmt.executeQuery("select * from \"Author\" where \"Author_ID\"=" + Id);
            if (resultSet.next()) {
                List<Book> books = new ArrayList<Book>();
                return new Author(resultSet.getLong("Author_ID"), resultSet.getString("Author_Name"), books);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(Author author) {
        return false;
    }

    @Override
    public boolean delete(Author author) {
        return false;
    }

    @Override
    public List<Author> retrieveAll() {
        List<Author> authors = new ArrayList<Author>();
        try {
            ResultSet resultSet = connection.createStatement().executeQuery("select * from \"Author\"");
            while (resultSet.next()) {
                Author author = retrieve(resultSet.getLong("Author_ID"));
                List<Long> bookIDs = retrieveBookIDSForAuthorID(resultSet.getLong("Author_ID"));
                List<Book> books = author.getBooks();
                for (Long bookId : bookIDs) {
                    books.add(Book.retrieve(bookId));
                }
                authors.add(author);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authors;
    }

    private List<Long> retrieveBookIDSForAuthorID(long authorId) {
        List<Long> authorIDList = new ArrayList<Long>();
        try {
            ResultSet resultSet = connection.createStatement().executeQuery("select \"Book_ID\" from \"AuthorBookAssociation\" where \"Author_ID\"=" + authorId);
            while (resultSet.next()) {
                authorIDList.add(resultSet.getLong("Book_ID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authorIDList;
    }
}
