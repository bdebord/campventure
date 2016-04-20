package com.example.owner.campventure;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateGetter {
    public static String getCurrentDate(){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyMMdd");
        String date = df.format(c.getTime());

        return date;
    }
}
