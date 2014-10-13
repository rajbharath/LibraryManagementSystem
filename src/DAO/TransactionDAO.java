package DAO;

import main.Transaction;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by ganeswari on 10/9/14.
 */
public class TransactionDAO extends AbstractDAO<Transaction> {

    @Override
    public long create(Transaction transaction) {
        int i = 0;
        try {
            String sql = "insert into \"Transaction\"(\"Transaction_ID\",\"Reader_ID\",\"Book_ID\",\"Start_Date\",\"Estimated_Due_Date\") values(nextval('\"Transaction_ID_Sequence\"')," + transaction.getReader().getReaderId() + "," + transaction.getBook().getBookId() + ",'" + new Date(transaction.getStartDate().getTime()) + "','" + new Date(transaction.getEstimatedDueDate().getTime()) + "')";
            System.out.println(sql);
            i = connection.createStatement().executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
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
