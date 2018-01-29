package com.proven.cat.a7ymedio;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;

/**
 * Created by dmora on 25/01/2018.
 */

public class Music extends Service {

    MediaPlayer player;

    //Create the service
    @Override
    public void onCreate() {
        Toast.makeText(this, "Servicio creado",
                Toast.LENGTH_SHORT).show();

        player = MediaPlayer.create(this, R.raw.supermario);
    }

    //Start the service
    @Override
    public int onStartCommand(Intent intent, int flags, int idArranque) {
        Toast.makeText(this, "Servicio arrancado " + idArranque,
                Toast.LENGTH_SHORT).show();

        player.start();
        return START_STICKY;
    }

    //Destroy the service
    @Override
    public void onDestroy() {
        Toast.makeText(this, "Servicio detenido",
                Toast.LENGTH_SHORT).show();

        player.stop();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
