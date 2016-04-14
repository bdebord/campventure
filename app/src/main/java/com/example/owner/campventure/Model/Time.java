package com.example.owner.campventure.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Owner on 4/13/2016.
 */
public class Time {

    @SerializedName("hour")
    public String hour;

    @SerializedName("minute")
    public String minute;

    public String getTime(){
        return "7:30"; //todo
    }
}
