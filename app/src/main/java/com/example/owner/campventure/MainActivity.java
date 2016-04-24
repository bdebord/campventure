package com.example.owner.campventure;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.owner.campventure.API.AstronomyAPI;
import com.example.owner.campventure.Model.AstronomyResponse;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


//http://api.wunderground.com/api/634dc40d11e172b6/astronomy/q/46060.json

public class MainActivity extends AppCompatActivity {
    int sunSetTimeMs;
    private Compass compass;
    TextView textSunset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonSubmit = (Button) findViewById(R.id.buttonSubmit);
        buttonSubmit.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View V) {
                        submitClicked();
                    }
                }
        );

        compass = new Compass(this);
        compass.arrowView = (ImageView) findViewById(R.id.compassArrow);

        GPSTracker gpsTracker = new GPSTracker(getApplicationContext());
        String zip = gpsTracker.getZip();

        EditText zipText = (EditText)findViewById(R.id.inputZip);
        zipText.setText(zip);

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

    private void submitClicked() {
        EditText inputZip = (EditText) findViewById(R.id.inputZip);
        String zip = inputZip.getText().toString();
        doAstroCall(zip);

        textSunset = (TextView) findViewById(R.id.textSunset);
    }

    private void doAstroCall(String zip) {
        AstronomyAPI api = new AstronomyAPI();
        api.getAstronomy(zip, new Callback<AstronomyResponse>() {
            @Override
            public void onResponse(Call<AstronomyResponse> call, Response<AstronomyResponse> response) {
                if (response.body().sunphase == null) {
                    showToast();
                    return;
                }
                Integer milliseconds = response.body().sunphase.getSecondsUntilSunset();  //sun_phase.getTime();

                new SunsetUpdateTimer(milliseconds, new SunsetUpdateTimer.UpdateTimerTickListener() {
                    @Override
                    public void onTick(int hours, int minutes, int seconds) {
                        textSunset.setText(hours + "h " + minutes + "m until sunset.");
                    }

                    @Override
                    public void onFinish() {
                        textSunset.setText("The sun has set.");
                    }
                }).start();

            }

            @Override
            public void onFailure(Call<AstronomyResponse> call, Throwable t) {

            }
        });
    }

    public void showToast(){
        Toast.makeText(getApplicationContext(),"Unable to find ZIP code, please try again later.", Toast.LENGTH_LONG).show();
    }

}
