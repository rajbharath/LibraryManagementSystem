package Test;

import main.Book;
import main.Library;
import main.Reader;
import main.Transaction;
import org.junit.Test;

/**
 * Created by ganeswari on 10/14/14.
 */
public class TransactionTest {
    @Test
    public void create() {
        Book book = Book.retrieve(1);
        Reader reader = Reader.retrieve(1);
        Transaction transaction = new Transaction(reader, book, Library.getToday(), Library.getDueDate(Library.getToday()), false);
        transaction.save();
    }
}
