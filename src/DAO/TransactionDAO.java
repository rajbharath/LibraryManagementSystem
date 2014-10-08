package DAO;

import src.Transaction;

import java.util.List;

/**
 * Created by ganeswari on 10/9/14.
 */
public class TransactionDAO  extends AbstractDAO<Transaction>{

    @Override
    public long create(Transaction transaction) {
        return 0;
    }

    @Override
    public Transaction retrieve(long Id) {
        return null;
    }
    public Transaction retrieve(long readerId,long bookId) {
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
