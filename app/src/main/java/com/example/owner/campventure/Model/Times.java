package com.example.owner.campventure.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Owner on 4/16/2016.
 */
public class Times {
    @SerializedName("hour")
    public String hour;

    @SerializedName("minute")
    public String minute;

    public String getTime(){
        return hour +":"+ minute;
    }
}
