package com.example.owner.campventure;

import android.os.CountDownTimer;

/**
 * Created by Owner on 4/19/2016.
 */
public class SunsetUpdateTimer extends CountDownTimer{

    public interface UpdateTimerTickListener {
        void onTick(int hours, int minutes, int seconds);
        void onFinish();
    }

    UpdateTimerTickListener listener;

    public SunsetUpdateTimer(int milliseconds, UpdateTimerTickListener listener) {
        super(milliseconds, 60000);
        this.listener = listener;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        int seconds =(int) millisUntilFinished / 1000;
        int hours = (seconds % 86400 ) / 3600 ;
        int minutes = ((seconds % 86400 ) % 3600 ) / 60;
        this.listener.onTick(hours, minutes, seconds);
    }

    @Override
    public void onFinish() {
        listener.onFinish();
    }
}
