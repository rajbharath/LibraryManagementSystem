package DAO;

import main.Publisher;

import java.util.List;

/**
 * Created by ganeswari on 10/12/14.
 */
public class PublisherDAO extends AbstractDAO<Publisher>{
    @Override
    public long create(Publisher publisher) {
        return 0;
    }

    @Override
    public Publisher retrieve(long Id) {
        return null;
    }

    @Override
    public boolean update(Publisher publisher) {
        return false;
    }

    @Override
    public boolean delete(Publisher publisher) {
        return false;
    }

    @Override
    public List<Publisher> retrieveAll() {
        return null;
    }
}
