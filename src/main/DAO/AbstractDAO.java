package main.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;

/**
 * Created by ganeswari on 10/9/14.
 */
public abstract class AbstractDAO<T> {
    Connection connection = null;
    Statement stmt = null;

    public AbstractDAO() {

        try {
            Class.forName(DBConfigReader.getDriverName());
            connection = DriverManager
                    .getConnection(DBConfigReader.getDBName(),
                            DBConfigReader.getUsername(), DBConfigReader.getPassword());
            stmt = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public abstract T create(T t);

    public abstract T retrieve(long Id);

    public abstract boolean update(T t);

    public abstract boolean delete(T t);

    public abstract List<T> retrieveAll();


}
