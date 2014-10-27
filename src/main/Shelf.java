package main;

import main.exception.BookNotFoundException;
import main.util.IOUtils;

import java.util.List;

/**
 * Created by ganeswari on 10/8/14.
 */
public class Shelf {
    List<Book> books;
    private static Shelf shelf = new Shelf();

    public static Shelf getInstance() {
        return shelf;
    }

    private Shelf() {
        books = Book.retrieveAll();
        IOUtils.print(books.size() + "shelf size");
    }

    public void add(Book book) {
        int index = books.indexOf(book);
        if (index != -1) {
            Book b = books.get(index);
            b.setCopies(b.getCopies() + book.getCopies());
        }
    }

    public void remove(Book book) throws BookNotFoundException {
        int index = books.indexOf(book);
        if (index != -1 && book.getCopies() > 0) {
            Book b = books.get(index);
            b.setCopies(b.getCopies() - 1);
            b.save();
        } else
            throw new BookNotFoundException();
    }

    public List<Book> search(SearchByType searchByType, String criteria) {
        SearchBy searchBy = SearchByFactory.create(searchByType);
        return searchBy.matches(criteria);
    }

}
