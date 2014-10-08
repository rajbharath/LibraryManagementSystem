package DAO;

import src.Reader;

import java.util.List;

/**
 * Created by ganeswari on 10/9/14.
 */
public class ReaderDAO extends  AbstractDAO<Reader>{


    @Override
    public long create(Reader reader) {
        return 0;
    }

    @Override
    public Reader retrieve(long Id) {
        return null;
    }

    @Override
    public boolean update(Reader reader) {
        return false;
    }

    @Override
    public boolean delete(Reader reader) {
        return false;
    }

    @Override
    public List<Reader> retrieveAll() {
        return null;
    }
}
