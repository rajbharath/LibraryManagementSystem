package DAO;

/**
 * Created by ganeswari on 10/9/14.
 */
public class DAOPool {
    public final static TransactionDAO transactionDAO = new TransactionDAO();
    public final static ReaderDAO readerDAO = new ReaderDAO();
    public final static BookDAO bookDAO = new BookDAO();
    public static PublisherDAO publisherDAO = new PublisherDAO();
}
