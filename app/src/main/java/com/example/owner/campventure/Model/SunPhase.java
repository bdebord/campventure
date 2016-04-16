package com.example.owner.campventure.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SunPhase{
    @SerializedName("sunrise")
    Times sunrise;

    @SerializedName("sunset")
    Times sunset;

    public String getSunsetTime(){
        String hour = sunset.hour;
        String minute = sunset.minute;

        return hour +":"+ minute;
    }

}
