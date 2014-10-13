package main;

import DAO.BookDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ganeswari on 10/8/14.
 */
public class Book {
    private long bookId;
    String title;
    String ISBN;
    String edition;
    Publisher publisher;
    List<Author> authors;
    boolean isPersistent = false;
    private static BookDAO bookDAO = new BookDAO();

    public Book(long bookId, String title, String ISBN, String edition, Publisher publisher, List<Author> authors, boolean isPersistent, Double price, int copies) {
        this.bookId = bookId;
        this.title = title;
        this.ISBN = ISBN;
        this.edition = edition;
        this.publisher = publisher;
        this.authors = authors;
        this.isPersistent = isPersistent;
        this.price = price;
        this.copies = copies;
    }


    public List<Author> getAuthors() {
        return authors;
    }

    Double price;
    int copies;


    public String getISBN() {
        return ISBN;
    }

    public String getEdition() {
        return edition;
    }

    public Double getPrice() {
        return price;
    }

    public String getTitle() {

        return title;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    public long getBookId() {
        return bookId;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public static List<Book> retrieveAll() {
        return bookDAO.retrieveAll();
    }

    public static Book retrieve(long bookId){
        return bookDAO.retrieve(bookId);
    }

    public static void delete(Book book){
        bookDAO.delete(book);
    }

    public void save() {
        if (isPersistent)
            bookDAO.update(this);
        else
            bookDAO.create(this);
    }

    public static List<Book> matches(String criteria) {

        List<Book> books = bookDAO.retrieveAll();

        List<Book> matchedBooks = new ArrayList<Book>();
        for (Book book : books) {
            if (book.getTitle().contains(criteria)) {
                matchedBooks.add(book);
            }
        }
        return matchedBooks;
    }
}
