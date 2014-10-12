package DAO;

import src.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by ganeswari on 10/9/14.
 */
public class BookDAO extends AbstractDAO<Book>{

    @Override
    public long create(Book book) {
        String sql = "insert into \"Book\" values("+ book.getBookId()+",'"+book.getTitle()+"')";
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
            ResultSet resultSet = stmt.executeQuery("Select * from \"Book\" where \"Book_ID\"="+Id+"");
            if(resultSet.next())
                return new Book(resultSet.getLong("Book_ID"),resultSet.getString("Name"),resultSet.getString("ISBN"),resultSet.getString("Edition"),resultSet.getLong("Publisher_ID"),resultSet.getDouble("Price"),resultSet.getInt("Copies"));
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
        return null;
    }
}
