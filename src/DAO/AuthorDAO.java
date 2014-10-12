package DAO;

import src.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ganeswari on 10/12/14.
 */
public class AuthorDAO extends  AbstractDAO<Author>
{
    @Override
    public long create(Author author) {
        return 0;
    }

    @Override
    public Author retrieve(long Id) {
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
            ResultSet resultSet = stmt.executeQuery("select * from \"Author\"");
            while(resultSet.next())
            {
                authors.add(new Author(resultSet.getLong(1),resultSet.getString(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authors;
    }
}
