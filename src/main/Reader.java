package main;

/**
 * Created by ganeswari on 10/8/14.
 */
public class Reader {
    long readerId;
    String name;

    public Reader(long readerId, String name) {
        this.readerId = readerId;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public long getReaderId() {
        return readerId;
    }

}