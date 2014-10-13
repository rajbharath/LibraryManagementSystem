package main;

import DAO.BookDAO;

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

    private static BookDAO bookDAO = new BookDAO();

    public Book(long bookId, String title, String ISBN, String edition, Publisher publisher, Double price, int copies,List<Author> authors) {
        this.bookId = bookId;
        this.title = title;
        this.ISBN = ISBN;
        this.edition = edition;
        this.publisher = publisher;
        this.price = price;
        this.copies = copies;
        this.authors =  authors;
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

}
