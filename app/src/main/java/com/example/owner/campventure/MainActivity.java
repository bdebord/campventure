package com.example.owner.campventure;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.example.owner.campventure.API.AstronomyAPI;
import com.example.owner.campventure.Model.AstronomyResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//http://api.wunderground.com/api/634dc40d11e172b6/astronomy/q/46060.json

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonSubmit = (Button)findViewById(R.id.buttonSubmit);

        buttonSubmit.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View V) {
                        submitClicked();
                    }
                }
        );
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void submitClicked() {
        AstronomyAPI api = new AstronomyAPI();
        api.getAstronomy("46060", new Callback<AstronomyResponse>() {
            @Override
            public void onResponse(Call<AstronomyResponse> call, Response<AstronomyResponse> response) {
            response.body().sunPhase.getSunsetTime();  //sun_phase.getTime();

            }

            @Override
            public void onFailure(Call<AstronomyResponse> call, Throwable t) {

            }
        });
    }

    private void timeApiCall() {

    }



}
