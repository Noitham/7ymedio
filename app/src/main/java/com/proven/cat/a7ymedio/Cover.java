package com.proven.cat.a7ymedio;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by dmora on 25/01/2018.
 */

public class Cover extends AppCompatActivity {

    //We define our elements
    private Button buttonStart;
    private Button buttonSettings;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ini_layout);

        buttonStart = (Button) findViewById(R.id.buttonStart);
        buttonSettings = (Button) findViewById(R.id.buttonSettings);

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startGame();
            }
        });
        buttonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSettings();
            }
        });
    }

    //Method for starting the game. We'll call for it when we press buttonStart
    public void startGame() {
        Intent start = new Intent(this, MainActivity.class);
        startActivity(start);
    }

    //Method for going to settings layout. We'll call it when we press buttonSettings
    public void setSettings() {
        Intent settings = new Intent(this, Settings.class);
        startActivity(settings);
    }

}
