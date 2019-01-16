package main.java.utils;


import java.sql.Date;
import java.text.SimpleDateFormat;

public class DateUtil {
    public static String transform(Date date){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String s = df.format(date);
        return s;
    }
}
