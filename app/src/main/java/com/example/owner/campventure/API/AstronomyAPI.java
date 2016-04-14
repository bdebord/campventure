package com.example.owner.campventure.API;

import android.util.Log;

import com.example.owner.campventure.Model.AstronomyResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

//http://api.wunderground.com/api/634dc40d11e172b6/astronomy/q/46060.json


public class AstronomyAPI {

    private final Retrofit retrofit;
    private final AstronomyAPIInterface api;

    public AstronomyAPI(){

        this.retrofit = new Retrofit.Builder()
                .baseUrl("http://api.wunderground.com/api/634dc40d11e172b6/astronomy/q/")
                .addConverterFactory(GsonConverterFactory.create()) //this adds in GSON
                .build(); //creates retrofit object

        this.api = this.retrofit.create(AstronomyAPIInterface.class); //Creates the AstronomyAPIInterface makes the request

    }

    public void getAstronomy(String zip, Callback<AstronomyResponse> callback){
        Call<AstronomyResponse> call = this.api.getAstronomy(zip);  //makes the call
        call.enqueue(callback);
    }
}
