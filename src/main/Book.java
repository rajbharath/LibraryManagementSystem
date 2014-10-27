package main;

import main.DAO.BookDAO;

import java.util.List;
import java.util.stream.Collectors;

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

    public static Book retrieve(long bookId) {
        return bookDAO.retrieve(bookId);
    }

    public static void delete(Book book) {
        bookDAO.delete(book);
    }

    public void save() {
        if (isPersistent)
            bookDAO.update(this);
        else
            bookDAO.create(this);
    }

    public static List<Book> matches(String criteria) {

        return bookDAO.retrieveMatchesByBookName(criteria);
    }

    public String display() {
        String displayString = "";
        displayString += "Title: " + getTitle() + "\n";
        displayString += "Authors: " + getAuthors().stream().map(a -> a.getName()).collect(Collectors.joining(","));
        return displayString;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (bookId != book.bookId) return false;
        if (copies != book.copies) return false;
        if (!ISBN.equals(book.ISBN)) return false;
        if (!edition.equals(book.edition)) return false;
        if (!title.equals(book.title)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public void setIsPersistent(boolean isPersistent) {
        this.isPersistent = isPersistent;
    }
}
