package com.example.owner.campventure.Model;

import com.example.owner.campventure.DateGetter;
import com.google.gson.annotations.SerializedName;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class SunPhase{
    @SerializedName("sunrise")
    public Times sunrise;

    @SerializedName("sunset")
    public Times sunset;

    public String getSunsetTime(){
        String hour = sunset.hour;
        String minute = sunset.minute;

        return hour +":"+ minute;
    }

    public int getSecondsUntilSunset() {
        int minutes = Integer.parseInt(sunset.minute);
        int hours = Integer.parseInt(sunset.hour);
        long sunset_time_seconds = 0;
        //int sunset_time_seconds = ((hours * 60) *60) + (minutes * 60);

        long current_time_seconds = System.currentTimeMillis();  //get current time in milliseconds
        String parsedDate = DateGetter.getCurrentDate()+sunset.hour+sunset.minute; //get sunset time string
        //SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSSZ"); //SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssZZZZ")
        DateFormat dF = new SimpleDateFormat("yyMMddHHmm");
        try {
            Date date = dF.parse(parsedDate);
            sunset_time_seconds = date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }


        //String asString = formatter.format(gmt);

        long seconds =  sunset_time_seconds - current_time_seconds;

        int milliseconds = (int) seconds;

        if (milliseconds < 0) {
            return 0;
        }else{
            return milliseconds;}
    }

}
