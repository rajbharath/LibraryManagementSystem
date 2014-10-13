package DAO;

import main.Reader;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by ganeswari on 10/9/14.
 */
public class ReaderDAO extends AbstractDAO<Reader> {


    @Override
    public long create(Reader reader) {
        return 0;
    }

    @Override
    public Reader retrieve(long readerId) {
        try {
            ResultSet resultSet = connection.createStatement().executeQuery("select * from \"Reader\" where \"Reader_ID\" =" + readerId);
            if (resultSet.next()) {
                return new Reader(resultSet.getLong("Reader_ID"), resultSet.getString("Reader_Name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(Reader reader) {
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
