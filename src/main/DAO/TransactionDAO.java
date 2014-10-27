package main.DAO;

import main.Book;
import main.Reader;
import main.Transaction;

import java.sql.*;
import java.util.List;

/**
 * Created by ganeswari on 10/9/14.
 */
public class TransactionDAO extends AbstractDAO<Transaction> {

    @Override
    public Transaction create(Transaction transaction) {
        try {
            String sql = "insert into \"Transaction\"(\"Transaction_ID\",\"Reader_ID\",\"Book_ID\",\"Start_Date\",\"Estimated_Due_Date\") values(nextval('\"Transaction_ID_Sequence\"')," + transaction.getReader().getReaderId() + "," + transaction.getBook().getBookId() + ",'" + new Date(transaction.getStartDate().getTime()) + "','" + new Date(transaction.getEstimatedDueDate().getTime()) + "')";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            int returnCode = statement.executeUpdate();
            if (returnCode == 0)
                throw new SQLException("Transaction Creation Failed, No Rows Affected");
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    transaction.setTransactionId(generatedKeys.getLong(1));
                    transaction.setIsPersistent(true);
                    return transaction;
                } else {
                    throw new SQLException("Creating Transaction failed, no ID obtained.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Transaction retrieve(long Id) {
        return null;
    }

    public Transaction retrieve(long readerId, long bookId) {
        String sql = "select * from \"Transaction\" where \"Book_ID\"=" + bookId + " and \"Reader_ID\"=" + readerId;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                Transaction transaction = new Transaction(Reader.retrieve(resultSet.getLong("Reader_ID")), Book.retrieve(resultSet.getLong("Book_ID")), resultSet.getDate("Start_Date"), resultSet.getDate("Estimated_Due_Date"), true);
                transaction.setTransactionId(resultSet.getLong("Transaction_ID"));
                return transaction;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean update(Transaction transaction) {
        String sql = "update \"Transaction\" set \"Returned_Date\"='now()' where \"Book_ID\"=" + transaction.getBook().getBookId() + " and \"Reader_ID\"=" + transaction.getReader().getReaderId();
        int returnCode = -1;
        try {

            Statement statement = connection.createStatement();
            returnCode = statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returnCode != -1;
    }

    @Override
    public boolean delete(Transaction transaction) {
        return false;
    }

    @Override
    public List<Transaction> retrieveAll() {
        return null;
    }
}
