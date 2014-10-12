package DAO;

import src.AuthorBookAssociation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ganeswari on 10/12/14.
 */
public class AuthorBookAssociationDAO extends AbstractDAO<AuthorBookAssociation> {

    @Override
    public long create(AuthorBookAssociation authorBookAssociation) {
        return 0;
    }

    @Override
    public AuthorBookAssociation retrieve(long associationID) {
        return null;
    }

    public List<AuthorBookAssociation> retrieveFor(long authorID) {
        List<AuthorBookAssociation> authorBookAssociations = new ArrayList<AuthorBookAssociation>();
        try {
            ResultSet resultSet = stmt.executeQuery("select * from \"AuthorBookAssociation\" where \"Author_ID\"=" + authorID + "");
            while (resultSet.next())
                authorBookAssociations.add(new AuthorBookAssociation(resultSet.getLong("Author_Book_Association_ID"), resultSet.getLong("Author_ID"), resultSet.getLong("Book_ID")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authorBookAssociations;
    }

    @Override
    public boolean update(AuthorBookAssociation authorBookAssociation) {
        return false;
    }

    @Override
    public boolean delete(AuthorBookAssociation authorBookAssociation) {
        return false;
    }

    @Override
    public List<AuthorBookAssociation> retrieveAll() {
        return null;
    }
}
