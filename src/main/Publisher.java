package main;

import main.DAO.PublisherDAO;

/**
 * Created by ganeswari on 10/8/14.
 */

public class Publisher {
    long publisherId;
    String name;
    boolean isPersistent;
    private final static PublisherDAO publisherDAO = new PublisherDAO();

    public long getPublisherId() {
        return publisherId;
    }

    public String getName() {
        return name;
    }

    public Publisher(long publisherId, String name, boolean isPersistent) {
        this.publisherId = publisherId;
        this.name = name;
        this.isPersistent = isPersistent;
    }

    public Publisher(long publisherId, String name) {
        this(publisherId, name, false);
    }

    public Publisher(String name) {
        this(0, name, false);
    }


    public void setPublisherId(long publisherId) {
        this.publisherId = publisherId;
    }

    public void save() {
        if (isPersistent)
            publisherDAO.update(this);
        else
            publisherDAO.create(this);
    }

    public void setPersistent(boolean persistent) {
        this.isPersistent = persistent;
    }
}
