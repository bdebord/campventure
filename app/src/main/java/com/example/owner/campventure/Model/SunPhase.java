package com.example.owner.campventure.Model;

import android.content.Context;
import android.widget.Toast;

import com.example.owner.campventure.DateGetter;
import com.example.owner.campventure.MainActivity;
import com.google.gson.annotations.SerializedName;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class SunPhase{
    @SerializedName("sunrise")
    public Times sunrise;

    @SerializedName("sunset")
    public Times sunset;

    public Integer getSecondsUntilSunset() {
        long sunset_time_seconds = 0;

        long current_time_seconds = System.currentTimeMillis();  //get current time in milliseconds
        String parsedDate = DateGetter.getCurrentDate()+sunset.hour+sunset.minute; //get sunset time string
        DateFormat dF = new SimpleDateFormat("yyMMddHHmm");
        try {
            Date date = dF.parse(parsedDate);
            sunset_time_seconds = date.getTime();
        } catch (ParseException e) {

        }

        long seconds =  sunset_time_seconds - current_time_seconds;

        int milliseconds = (int) seconds;

        if (milliseconds < 0) {  //checks if sun has already set
            return 0;
        }else{
            return milliseconds;}
    }

}
