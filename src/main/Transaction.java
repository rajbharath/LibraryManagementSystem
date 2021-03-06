package main;

import main.DAO.TransactionDAO;

import java.util.Date;

/**
 * Created by ganeswari on 10/9/14.
 */

public class Transaction {

    long transactionId;
    Reader reader;
    Book book;
    Date startDate;
    Date estimatedDueDate;
    Date returnedDate;
    TransactionType transactionType;
    private static final TransactionDAO transactionDAO = new TransactionDAO();
    boolean isPersistent;

    public Transaction(Reader reader, Book book, Date startDate, Date estimatedDueDate, boolean isPersistent) {
        this.reader = reader;
        this.book = book;
        this.startDate = startDate;
        this.estimatedDueDate = estimatedDueDate;
        this.transactionType = TransactionType.ISSUE;
        this.isPersistent = isPersistent;
    }

    public void update(Date returnedDate) {
        this.returnedDate = returnedDate;
    }

    public static Transaction retrieve(long readerId, long bookId) {
        return transactionDAO.retrieve(readerId, bookId);
    }

    public void save() {
        if (isPersistent)
            transactionDAO.update(this);
        else
            transactionDAO.create(this);
    }

    public Reader getReader() {
        return reader;
    }

    public Book getBook() {
        return book;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEstimatedDueDate() {
        return estimatedDueDate;
    }

    public Date getReturnedDate() {
        return returnedDate;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public boolean isPersistent() {
        return isPersistent;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public void setIsPersistent(boolean isPersistent) {
        this.isPersistent = isPersistent;
    }

    public void setPersistent(boolean isPersistent) {
        this.isPersistent = isPersistent;
    }

    public long getTransactionId() {
        return transactionId;
    }
}
