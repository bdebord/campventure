package com.example.owner.campventure;

import android.os.Bundle;
import android.preference.EditTextPreference;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.owner.campventure.API.AstronomyAPI;
import com.example.owner.campventure.Model.AstronomyResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//http://api.wunderground.com/api/634dc40d11e172b6/astronomy/q/46060.json

public class MainActivity extends AppCompatActivity {
public static String sunSetTime = "";
    private Compass compass;

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

        compass = new Compass(this);
        compass.arrowView = (ImageView) findViewById(R.id.compassArrow);
    }
    @Override
    protected void onStart() {
        super.onStart();
        compass.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        compass.stop();
    }
    @Override
    protected void onResume() {
        super.onResume();
        compass.start();
    }
    @Override
    protected void onStop() {
        super.onStop();
        compass.stop();
    }

/*
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
    } */

    private void submitClicked() {
        EditText inputZip = (EditText) findViewById(R.id.inputZip);
        String zip = inputZip.getText().toString();

        AstronomyAPI api = new AstronomyAPI();
        api.getAstronomy(zip, new Callback<AstronomyResponse>() {
            @Override
            public void onResponse(Call<AstronomyResponse> call, Response<AstronomyResponse> response) {
                sunSetTime = response.body().sunphase.getSunsetTime();  //sun_phase.getTime();
                TextView textSunset = (TextView) findViewById(R.id.textSunset);
                textSunset.setText("The sun will set at " + sunSetTime + ".");

            }

            @Override
            public void onFailure(Call<AstronomyResponse> call, Throwable t) {

            }
        });

    }
    }
