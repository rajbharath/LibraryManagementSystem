package main.DAO;

import main.Book;
import main.Reader;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by ganeswari on 10/9/14.
 */
public class ReaderDAO extends AbstractDAO<Reader> {


    @Override
    public Reader create(Reader reader) {
        return null;
    }

    @Override
    public Reader retrieve(long readerId) {
        try {
            String sql = "select * from \"Reader\" where \"Reader_ID\" =" + readerId;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {

                Reader reader = new Reader(resultSet.getLong("Reader_ID"), resultSet.getString("Reader_Name"), true);
                sql = "select \"Book_ID\" from \"Transaction\" where \"Reader_ID\"=" + readerId + " and \"Returned_Date\" IS NULL";
                ResultSet rs = statement.executeQuery(sql);
                Set<Book> books = new HashSet<>();
                while (rs.next()) {
                    Book book = Book.retrieve(rs.getLong("Book_ID"));
                    if (book != null)
                        books.add(book);
                }

                reader.setBooks(books);
                return reader;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(Reader reader) {
        try {
            connection.setAutoCommit(false);
            String sql = "update \"Reader\" set \"Reader_Name\"='" + reader.getName() + "' where \"Reader_ID\"=" + reader.getReaderId();
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Reader reader) {
        return false;
    }

    @Override
    public List<Reader> retrieveAll() {
        return null;
    }
}
