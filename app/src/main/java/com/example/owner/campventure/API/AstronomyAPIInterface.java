package com.example.owner.campventure.API;

import com.example.owner.campventure.Model.AstronomyResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AstronomyAPIInterface {
    @GET ("{zip}.json") //retrofit OLD: @GET ("{zip}.json")
    Call<AstronomyResponse> getAstronomy(@Path("zip")String zip); //me
}
