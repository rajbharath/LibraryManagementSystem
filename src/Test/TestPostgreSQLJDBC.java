package Test;

import main.DAO.DBConfigReader;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestPostgreSQLJDBC {
    public static void main(String args[]) {
        Connection c = null;
        try {
            Class.forName(DBConfigReader.getDriverName());
            c = DriverManager
                    .getConnection(DBConfigReader.getDBName(),
                            DBConfigReader.getUsername(), DBConfigReader.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }
}