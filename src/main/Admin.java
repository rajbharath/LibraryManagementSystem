
package main;

import main.DAO.AdminDAO;
import main.exception.BookNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ganeswari on 10/8/14.
 */
public class Admin {

    Shelf shelf;
    long adminId;
    String adminName;
    static AdminDAO adminDAO = new AdminDAO();
    String password;
    boolean isPersistent;

    public Admin(String adminName, String password, boolean isPersistent) {
        this.adminName = adminName;
        this.password = password;
        shelf = Shelf.getInstance();
        this.isPersistent = isPersistent;

    }

    public Admin(String username, String password) {
        this(username, password, true);
    }

    public void issue(Reader reader, Book book) {
        if (reader.addBook(book)) {
            reader.save();
            Transaction transaction = new Transaction(reader, book, Library.getToday(), Library.getDueDate(Library.getToday()), false);
            transaction.save();
            book.setCopies(book.getCopies() - 1);
            book.save();
        }
    }

    public void renew(Reader reader, Book book) {
        //TO DO
    }

    public void takeBack(Reader reader, Book book) {
        if (reader.remove(book)) {
            reader.save();
            Transaction transaction = Transaction.retrieve(reader.getReaderId(), book.getBookId());
            transaction.update(Library.getToday());
            transaction.save();
            book.setCopies(book.getCopies() + 1);
            book.save();
        }
    }

    public void addBook(Book book) {
        shelf.add(book);
    }

    public void addBooks(List<Book> books) {

        for (Book book : books) {
            addBook(book);
        }
    }

    public void removeBook(Book book) {
        try {
            Book.delete(book);
            shelf.remove(book);
        } catch (BookNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void removeBooks(List<Book> books) {
        for (Book book : books) {
            removeBook(book);
        }
    }

    public static Admin retrieve(String username, String password) {

        return adminDAO.retrieve(username, password);
    }


    public boolean save() {
        if (isPersistent)
            return adminDAO.update(this);
        else
            return adminDAO.create(this) != null;
    }

    public List<Book> searchBooksByTitle(String title) {
        return shelf.search(SearchByType.TITLE, title);
    }

    public List<Book> searchBookByAuthorName(String authorName) {
        return shelf.search(SearchByType.AUTHOR, authorName);
    }

    public void addBook(String title, String isbn, String edition, String publisherName, String[] authorNames, int copies, double price) {
        Publisher publisher = new Publisher(publisherName);
        publisher.save();
        List<Author> authors = new ArrayList<>();
        for (String authorName : authorNames) {
            Author author = new Author(authorName);
            author.save();
            authors.add(author);
        }
        Book book = new Book(0, title, isbn, edition, publisher, authors, false, price, copies);
        book.save();
        shelf.add(book);
    }

    public List<Book> getHistory(Reader reader) {
        return new ArrayList<>(reader.getBooks());
    }
}
