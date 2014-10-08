package src;

/**
 * Created by ganeswari on 10/8/14.
 */
public class Book {
    private long bookId;
    String title;
    Author author;
    String ISBN;
    String edition;
    long publisherId;

    public Book(long bookId, String title, Author author, String ISBN, String edition, long publisherId, Double price, int copies) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.edition = edition;
        this.publisherId = publisherId;
        this.price = price;
        this.copies = copies;
    }

    Double price;
    int copies;

    public Author getAuthor() {
        return author;
    }

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

    public int getCopies(){
        return copies;
    }

    public void setCopies(int copies){
        this.copies = copies;
    }

    public long getBookId() {
        return bookId;
    }

    public long getPublisherId() {
        return publisherId;
    }
}
