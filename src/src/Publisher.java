package src;

/**
 * Created by ganeswari on 10/8/14.
 */

public class Publisher {
    long publisherId;
    String name;


    public long getPublisherId() {
        return publisherId;
    }

    public String getName() {
        return name;
    }

    public Publisher(long publisherId, String name) {
        this.publisherId = publisherId;
        this.name = name;
    }
}
