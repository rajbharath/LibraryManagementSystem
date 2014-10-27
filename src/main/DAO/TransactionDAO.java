package main.DAO;

import main.Transaction;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by ganeswari on 10/9/14.
 */
public class TransactionDAO extends AbstractDAO<Transaction> {

    @Override
    public Transaction create(Transaction transaction) {
        try {
            String sql = "insert into \"Transaction\"(\"Transaction_ID\",\"Reader_ID\",\"Book_ID\",\"Start_Date\",\"Estimated_Due_Date\") values(nextval('\"Transaction_ID_Sequence\"')," + transaction.getReader().getReaderId() + "," + transaction.getBook().getBookId() + ",'" + new Date(transaction.getStartDate().getTime()) + "','" + new Date(transaction.getEstimatedDueDate().getTime()) + "')";
            Statement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            int returnCode = statement.executeUpdate(sql);
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
        return null;
    }

    @Override
    public boolean update(Transaction transaction) {
        return false;
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
