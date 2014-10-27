package main.DAO;

import main.Author;
import main.Book;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by ganeswari on 10/12/14.
 */
public class AuthorDAO extends AbstractDAO<Author> {
    @Override
    public Author create(Author author) {
        try {
            String sql = "insert into \"Author\"(\"Author_ID\",\"Author_Name\") values(nextval('\"Author_ID_Sequence\"'),'" + author.getName() + "')";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            int returnCode = statement.executeUpdate();
            if (returnCode == 0)
                throw new SQLException("Author Creation Failed, No Rows Affected");
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    author.setAuthorId(generatedKeys.getLong(1));
                    author.setIsPersistent(true);
                    return author;
                } else {
                    throw new SQLException("Creating Author failed, no ID obtained.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Author retrieve(long Id) {
        try {
            ResultSet resultSet = stmt.executeQuery("select * from \"Author\" where \"Author_ID\"=" + Id);
            if (resultSet.next()) {
                List<Book> books = new ArrayList<Book>();
                return new Author(resultSet.getLong("Author_ID"), resultSet.getString("Author_Name"), books, true);
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

    public List<Author> retrieveMatchesByName(String criteria) {
        List<Author> authors = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String sql = "select distinct * from \"Author\" where lower(\"Author_Name\") like '%" + criteria.toLowerCase() + "%'\n";
            ResultSet resultSet = statement.executeQuery(sql);
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
        Set<Long> authorIDList = new HashSet<Long>();
        try {
            ResultSet resultSet = connection.createStatement().executeQuery("select distinct \"Book_ID\" from \"AuthorBookAssociation\" where \"Author_ID\"=" + authorId);
            while (resultSet.next()) {
                authorIDList.add(resultSet.getLong("Book_ID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>(authorIDList);
    }
}
