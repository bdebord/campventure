package com.example.owner.campventure.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Owner on 4/13/2016.
 */
public class AstronomyResponse {

 /*
   @SerializedName("moon_phase")
    MoonPhase moonPhase;

    @SerializedName("sunrise")
    public Time sunrise;

    @SerializedName("sunset")
    public Time sunset; */

 @SerializedName("sun_phase")
  public SunPhase sunphase;

}
