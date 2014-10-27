package main.DAO;

import main.Admin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by ganeswari on 10/18/14.
 */
public class AdminDAO extends AbstractDAO<Admin> {
    @Override
    public Admin create(Admin admin) {
        return null;

    }

    @Override
    public Admin retrieve(long Id) {
        try {
            ResultSet resultSet = stmt.executeQuery("select \"Admin_Name\", \"Password\" from \"Admin\" where \"Admin_ID\" =" + Id);
            if (resultSet.next()) {
                return new Admin(resultSet.getString("Admin_Name"), resultSet.getString("Password"), true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public boolean update(Admin admin) {
        return false;
    }

    @Override
    public boolean delete(Admin admin) {
        return false;
    }

    @Override
    public List<Admin> retrieveAll() {
        return null;
    }

    public Admin retrieve(String username, String password) {
        String sql = "select \"Admin_Name\",\"Password\" from \"Admin\" where \"Admin_Name\" ='" + username + "' and  \"Password\"='" + password + "' ";

        try {
            java.sql.Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);
            if (resultSet.next()) {
                return new Admin(resultSet.getString("Admin_Name"), resultSet.getString("Password"), true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
