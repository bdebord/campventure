package com.example.owner.campventure.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Owner on 4/13/2016.
 */
public class AstronomyResponse {

   @SerializedName("moon_phase")
    MoonPhase moonPhase;

    @SerializedName("sunrise")
    public Time sunrise;

    @SerializedName("sunset")
    public Time sunset;

 /*   @SerializedName("moon_phase")
    MoonPhase moonPhase;

    @SerializedName("sun_phase")
    SunPhase sunPhase;  */


}
