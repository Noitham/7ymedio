package com.proven.cat.a7ymedio;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Handler;
import android.os.Message;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by dmora on 26/01/2018.
 */

public class Crono extends Service {

    private Timer temporizer = new Timer();
    private static final long UPDATE_INTERVAL = 10; // ms
    public static MainActivity UPDATE_LISTENER;//
    private double crono = 0; //updates main activity
    private Handler handler;

    /***
     * Stablishes the receiver activity that will receive the data of the service (crono updates).
     */
    public static void setUpdateListener(MainActivity M1) {
        UPDATE_LISTENER = M1;
    }

    @SuppressLint("HandlerLeak")
    @Override
    public void onCreate() {
        super.onCreate();
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                UPDATE_LISTENER.updateCrono(crono);//Calling the service from the main activity
            }

        };
        incrementCrono();
    }

    //Stops the crono and destroys the service
    @Override
    public void onDestroy() {
        stopCrono();
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    //Increments the value of the crono, and updates it on the main activity
    private void incrementCrono() {
        temporizer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                crono += 0.01; //Addition of time
                handler.sendEmptyMessage(0);//From the thread we send the signal to the handler
                // The job finishes (increments value) so the main activity updates
            }
        }, 0, UPDATE_INTERVAL);
    }

    //Stops the crono
    private void stopCrono() {
        if (temporizer != null)
            temporizer.cancel();
    }

}
