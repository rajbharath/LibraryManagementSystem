
package src;

import java.util.List;

/**
 * Created by ganeswari on 10/8/14.
 */
public class Admin {

    Shelf shelf;

    public Admin() {
        shelf = Shelf.getInstance();
    }

    public void issue(Reader reader, Book book) {

        try {
            shelf.remove(book);
            Transaction transaction = new Transaction(reader.getReaderId(), book.getBookId(), Library.getToday(), Library.getDueDate(Library.getToday()));
        } catch (BookNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void renew(Reader reader, Book book) {
        Transaction transaction = Transaction.retrieve(reader.getReaderId(), book.getBookId());
        transaction.update(Library.getToday());
        transaction = new Transaction(reader.getReaderId(), book.getBookId(), Library.getToday(), Library.getDueDate(Library.getToday()));
    }

    public void takeBack(Reader reader, Book book) {
        Transaction transaction = Transaction.retrieve(reader.getReaderId(), book.getBookId());
        transaction.update(Library.getToday());
        addBook(book);
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
}
