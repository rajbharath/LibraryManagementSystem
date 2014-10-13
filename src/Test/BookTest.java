package Test;

import org.junit.Test;
import main.Author;
import main.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ganeswari on 10/12/14.
 */
public class BookTest {
    @Test
    public void testRetrieveAll() throws Exception {
        List<Book> books = new ArrayList<Book>();
        books.addAll(Book.retrieveAll());
        for(Book book:books){
            System.out.println(book.getTitle());
        }
    }

    @Test
    public void testMatches(){
        List<Book> books = Book.matches("Sun");
        for(Book book:books){
            System.out.println(book.getTitle());
        }
    }
}
