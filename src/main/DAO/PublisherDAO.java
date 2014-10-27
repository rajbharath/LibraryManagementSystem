package main.DAO;

import main.Publisher;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by ganeswari on 10/12/14.
 */
public class PublisherDAO extends AbstractDAO<Publisher> {
    @Override
    public Publisher create(Publisher publisher) {
        try {
            String sql = "insert into \"Publisher\"(\"Publisher_ID\",\"Publisher_Name\") values(nextval('\"Publisher_ID_Sequence\"'),'" + publisher.getName() + "')";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            int returnCode = statement.executeUpdate();
            if (returnCode == 0)
                throw new SQLException("Publisher Creation Failed, No Rows Affected");
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    publisher.setPublisherId(generatedKeys.getLong(1));
                    publisher.setPersistent(true);
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return publisher;
    }

    @Override
    public Publisher retrieve(long Id) {
        String sql = "select * from \"Publisher\" where \"Publisher_ID\"=" + Id;
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return new Publisher(resultSet.getLong("Publisher_ID"), resultSet.getString("Publisher_Name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean update(Publisher publisher) {
        return false;
    }

    @Override
    public boolean delete(Publisher publisher) {
        return false;
    }

    @Override
    public List<Publisher> retrieveAll() {
        return null;
    }
}
