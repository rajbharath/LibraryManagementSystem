package DAO;

import src.Book;

import java.util.List;

/**
 * Created by ganeswari on 10/9/14.
 */
public class BookDAO extends AbstractDAO<Book>{

    @Override
    public long create(Book book) {
        return 0;
    }

    @Override
    public Book retrieve(long Id) {
        return null;
    }

    @Override
    public boolean update(Book object) {
        return false;
    }

    @Override
    public boolean delete(Book object) {
        return false;
    }

    @Override
    public List<Book> retrieveAll() {
        return null;
    }
}
