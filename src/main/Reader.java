package main;

import main.DAO.ReaderDAO;

/**
 * Created by ganeswari on 10/8/14.
 */
public class Reader {
    long readerId;
    String name;
    private static final ReaderDAO readerDAO = new ReaderDAO();

    public Reader(long readerId, String name) {
        this.readerId = readerId;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public long getReaderId() {
        return readerId;
    }

    public static Reader retrieve(long readerId) {
        return readerDAO.retrieve(readerId);
    }
}
