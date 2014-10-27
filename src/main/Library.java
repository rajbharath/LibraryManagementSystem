package main;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by ganeswari on 10/9/14.
 */
public class Library {
    public static final int MAX_NUMBER_OF_BOOKS_TO_TAKE = 3;
    private static Calendar calendar = Calendar.getInstance();
    public static int RENTAL_PERIOD = 15;

    public static Date getToday() {
        calendar.setTimeInMillis(System.currentTimeMillis());
        return calendar.getTime();
    }

    public static Date getDueDate(Date date) {
        calendar.setTime(date);
        calendar.add(Calendar.DATE, RENTAL_PERIOD);
        return calendar.getTime();
    }

}
