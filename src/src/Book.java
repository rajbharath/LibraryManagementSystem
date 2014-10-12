package src;

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
    long publisherId;

    private static BookDAO bookDAO = new BookDAO();

    public Book(long bookId, String title, String ISBN, String edition, long publisherId, Double price, int copies) {
        this.bookId = bookId;
        this.title = title;
        this.ISBN = ISBN;
        this.edition = edition;
        this.publisherId = publisherId;
        this.price = price;
        this.copies = copies;
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

    public long getPublisherId() {
        return publisherId;
    }

    public void save() {

    }

    public static List<Book> retrieveAll(List<AuthorBookAssociation> authorBookAssociations) {
        List<Book> books = new ArrayList<Book>();
        for (AuthorBookAssociation authorBookAssociation : authorBookAssociations) {
            books.add(bookDAO.retrieve(authorBookAssociation.getBookId()));
        }
        return books;
    }
}
