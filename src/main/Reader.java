package main;

import main.DAO.ReaderDAO;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by ganeswari on 10/8/14.
 */
public class Reader {
    long readerId;
    String name;
    private static final ReaderDAO readerDAO = new ReaderDAO();
    Set<Book> books;
    boolean isPersistent;

    public Reader(long readerId, String name, boolean isPersistent) {
        this.readerId = readerId;
        this.name = name;
        books = new HashSet<>();
        this.isPersistent = isPersistent;
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


    public boolean addBook(Book book) {
        return books.size() < Library.MAX_NUMBER_OF_BOOKS_TO_TAKE && books.add(book);
    }

    public Set<Book> getBooks() {
        return books;
    }

    public boolean save() {
        if (isPersistent)
            return readerDAO.update(this);
        else
            return readerDAO.create(this) != null;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    public boolean remove(Book book) {
        return books.remove(book);
    }
}
