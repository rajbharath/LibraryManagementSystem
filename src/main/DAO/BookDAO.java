package main.DAO;

import main.Author;
import main.Book;
import main.Publisher;
import main.util.IOUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ganeswari on 10/9/14.
 */
public class BookDAO extends AbstractDAO<Book> {

    @Override
    public Book create(Book book) {


        try {
            String sql = "insert into \"Book\" values(nextval('\"Book_ID_Sequence\"'),'" + book.getTitle() + "','" + book.getISBN() + "','" + book.getEdition() + "'," + book.getPublisher().getPublisherId() + "," + book.getPrice() + "," + book.getCopies() + ")";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            int returnCode = statement.executeUpdate();
            if (returnCode == 0)
                throw new SQLException("Book Creation Failed, No Rows Affected");
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    book.setBookId(generatedKeys.getLong(1));
                    book.setIsPersistent(true);
                    List<Author> authors = book.getAuthors();
                    for (Author author : authors) {
                        sql = "insert into \"AuthorBookAssociation\"(\"Author_Book_Association_ID\",\"Book_ID\",\"Author_ID\") values(nextval('\"Author_Book_Association_ID_Sequence\"')," + book.getBookId() + "," + author.getAuthorId() + ")";
                        statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                        returnCode = statement.executeUpdate();
                        if (returnCode == 0)
                            throw new SQLException("Author Book Link Creation Failed, No Rows Affected");
                    }
                    return book;
                } else {
                    throw new SQLException("Creating Book failed, no ID obtained.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Book retrieve(long Id) {
        try {
            ResultSet resultSet = stmt.executeQuery("Select * from \"Book\" where \"Book_ID\"=" + Id + "");
            if (resultSet.next()) {
                Publisher publisher = Publisher.retrieve(resultSet.getLong("Publisher_ID"));
                List<Long> authorIDs = retrieveAuthorIDSForBookID(resultSet.getLong("Book_ID"));
                List<Author> authors = new ArrayList<Author>();
                for (Long authorID : authorIDs) {
                    authors.add(Author.retrieve(authorID));
                }
                return new Book(resultSet.getLong("Book_ID"), resultSet.getString("Name"), resultSet.getString("ISBN"), resultSet.getString("Edition"), publisher, authors, true, resultSet.getDouble("Price"), resultSet.getInt("Copies"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(Book book) {
        if (book.getPublisher() == null) IOUtils.print("null");
        String sql = "update \"Book\" set \"Name\"='" + book.getTitle() + "',\"ISBN\"='" + book.getISBN() + "',\"Edition\"='" + book.getEdition() + "',\"Publisher_ID\"=" + book.getPublisher().getPublisherId() + ",\"Price\"=" + book.getPrice() + ",\"Copies\"=" + book.getCopies() + " where \"Book_ID\"=" + book.getBookId();
        int returnCode = -1;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return returnCode >= 0;
    }

    @Override
    public boolean delete(Book book) {

        String sql = "Delete from \"Book\" where \"Book_ID\" = " + book.getBookId() + "";
        Statement statement = null;
        int returnCode = -1;
        try {
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            returnCode = statement.executeUpdate(sql);
            sql = "Delete from \"AuthorBookAssociation\" where \"Book_ID\"=" + book.getBookId() + "";
            returnCode = statement.executeUpdate(sql);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returnCode != -1;
    }

    @Override
    public List<Book> retrieveAll() {
        List<Book> books = new ArrayList<Book>();
        try {
            ResultSet resultSet = stmt.executeQuery("Select * from \"Book\" ");
            while (resultSet.next()) {
                Publisher publisher = Publisher.retrieve(resultSet.getLong("Publisher_ID"));
                List<Long> authorIDs = retrieveAuthorIDSForBookID(resultSet.getLong("Book_ID"));
                List<Author> authors = new ArrayList<Author>();
                for (Long authorID : authorIDs) {
                    authors.add(Author.retrieve(authorID));
                }
                if (publisher == null) IOUtils.print("publisher null in creation");
                books.add(new Book(resultSet.getLong("Book_ID"), resultSet.getString("Name"), resultSet.getString("ISBN"), resultSet.getString("Edition"), publisher, authors, true, resultSet.getDouble("Price"), resultSet.getInt("Copies")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    private List<Long> retrieveAuthorIDSForBookID(long book_id) {
        List<Long> authorIDList = new ArrayList<Long>();
        try {
            ResultSet resultSet = connection.createStatement().executeQuery("select \"Author_ID\" from \"AuthorBookAssociation\" where \"Book_ID\"=" + book_id);
            while (resultSet.next()) {
                authorIDList.add(resultSet.getLong("Author_ID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authorIDList;
    }

    public List<Book> retrieveMatchesByBookName(String criteria) {
        List<Book> books = new ArrayList<Book>();
        String sql = "Select * from \"Book\" where Lower(\"Name\") like '%" + criteria.toLowerCase() + "%'";
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Publisher publisher = Publisher.retrieve(resultSet.getLong("Publisher_ID"));
                List<Long> authorIDs = retrieveAuthorIDSForBookID(resultSet.getLong("Book_ID"));
                List<Author> authors = new ArrayList<Author>();
                for (Long authorID : authorIDs) {
                    authors.add(Author.retrieve(authorID));
                }
                if (publisher == null) IOUtils.print("publisher null in creation");
                books.add(new Book(resultSet.getLong("Book_ID"), resultSet.getString("Name"), resultSet.getString("ISBN"), resultSet.getString("Edition"), publisher, authors, true, resultSet.getDouble("Price"), resultSet.getInt("Copies")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

}
