package src;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by ganeswari on 10/9/14.
 */
public class Library {
    private static Calendar calendar = Calendar.getInstance();
    public static int RENTAL_PERIOD=15;
    public static Date getToday(){
        calendar.setTimeInMillis(System.currentTimeMillis());
        return calendar.getTime();
    }

    public static Date getDueDate(Date date){
        calendar.setTime(date);
        calendar.add(Calendar.DATE,RENTAL_PERIOD);
        return calendar.getTime();
    }

}
