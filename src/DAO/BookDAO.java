package DAO;

import main.Author;
import main.Book;
import main.Publisher;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ganeswari on 10/9/14.
 */
public class BookDAO extends AbstractDAO<Book> {

    @Override
    public long create(Book book) {
        String sql = "insert into \"Book\" values(" + book.getBookId() + ",'" + book.getTitle() + "')";
        try {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public Book retrieve(long Id) {
        try {
            ResultSet resultSet = stmt.executeQuery("Select * from \"Book\" where \"Book_ID\"=" + Id + "");
            if (resultSet.next()) {
                Publisher publisher = DAOPool.publisherDAO.retrieve(resultSet.getLong("Publisher_ID"));
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
    public boolean update(Book object) {
        return false;
    }

    @Override
    public boolean delete(Book object) {
        return false;
    }

    @Override
    public List<Book> retrieveAll() {
        List<Book> books = new ArrayList<Book>();
        try {
            ResultSet resultSet = stmt.executeQuery("Select * from \"Book\" ");
            while (resultSet.next()) {
                Publisher publisher = DAOPool.publisherDAO.retrieve(resultSet.getLong("Publisher_ID"));
                List<Long> authorIDs = retrieveAuthorIDSForBookID(resultSet.getLong("Book_ID"));
                List<Author> authors = new ArrayList<Author>();
                for (Long authorID : authorIDs) {
                    authors.add(Author.retrieve(authorID));
                }
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

}
