package DAO;

import java.util.List;

/**
 * Created by ganeswari on 10/9/14.
 */
public abstract class AbstractDAO<T> {
    public abstract long create(T t);
    public abstract T retrieve(long Id);
    public abstract boolean update(T t);
    public abstract boolean delete(T t);
    public abstract List<T> retrieveAll();
}
