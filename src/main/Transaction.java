package main;

import DAO.DAOPool;
import DAO.TransactionDAO;

import java.util.Date;

/**
 * Created by ganeswari on 10/9/14.
 */

public class Transaction {

    long transactionId;
    long readerId;
    long bookId;
    Date startDate;
    Date estimatedDueDate;
    Date returnedDate;
    TransactionType transactionType;
    TransactionDAO transactionDAO = DAOPool.transactionDAO;

    public Transaction(long readerId, long bookId, Date startDate, Date estimatedDueDate) {
        this.readerId = readerId;
        this.bookId = bookId;
        this.startDate = startDate;
        this.estimatedDueDate = estimatedDueDate;
        this.transactionType = TransactionType.ISSUE;
        this.transactionId =transactionDAO.create(this);
    }

    public void update(Date returnedDate) {
        this.returnedDate = returnedDate;

    }

    public static Transaction retrieve(long readerId, long bookId) {
        return retrieve(readerId, bookId);
    }
}
