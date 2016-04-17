package com.example.owner.campventure;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.owner.campventure.API.AstronomyAPI;
import com.example.owner.campventure.Model.AstronomyResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.os.CountDownTimer;


//http://api.wunderground.com/api/634dc40d11e172b6/astronomy/q/46060.json

public class MainActivity extends AppCompatActivity {
    int sunSetTimeMs;
    private Compass compass;
    TextView textSunset;

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

    private void submitClicked() {
        EditText inputZip = (EditText) findViewById(R.id.inputZip);
        String zip = inputZip.getText().toString();
        doAstroCall(zip);

        textSunset = (TextView) findViewById(R.id.textSunset);

        //sunSetTimeMs = (int) sunSetTime.getVal();

    }
    private void doAstroCall(String zip){
        AstronomyAPI api = new AstronomyAPI();
        api.getAstronomy(zip, new Callback<AstronomyResponse>() {
            @Override
            public void onResponse(Call<AstronomyResponse> call, Response<AstronomyResponse> response) {
                int temp;
                temp = response.body().sunphase.getSecondsUntilSunset();  //sun_phase.getTime();

                new CountDownTimer(temp, 60000) {
                    public void onTick(long millisUntilFinished) {
                        int seconds =(int) millisUntilFinished / 1000;
                        int hours = (seconds % 86400 ) / 3600 ;
                        int minutes = ((seconds % 86400 ) % 3600 ) / 60;

                        textSunset.setText(hours + "h " + minutes + "m until sunset.");
                    }
                    public void onFinish() {
                        textSunset.setText("The sun has set.");
                    }

                }.start();


            }

            @Override
            public void onFailure(Call<AstronomyResponse> call, Throwable t) {

            }
        });
    }



}
