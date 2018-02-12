package com.proven.cat.a7ymedio;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * Created by alumne on 21/11/17.
 */

public class Settings extends AppCompatActivity {

    //We define our objects
    Bundle settingsData;

    View.OnClickListener listener;
    RadioGroup.OnCheckedChangeListener listenerRadio;

    RadioGroup radioGroup;
    CheckBox checkbox;
    Button sendButton;
    EditText txtInput;
    TextView txtName;
    TextView txtDifficulty;
    TextView txtSettings;

    RadioButton radioButton1;
    RadioButton radioButton2;

    private LinearLayout settingsLayout;

    TextView textSeekBar;
    SeekBar difficultySeekBar = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_layout);

        // Initialize the elements of the form
        sendButton = (Button)
                findViewById(R.id.sendButton);

        checkbox = (CheckBox) findViewById(R.id.checkBox);
        checkbox.setChecked(false);

        controlSeekBar();

        radioGroup = (RadioGroup) findViewById(R.id.gruporb);
        radioGroup.check(R.id.radiob1);

        txtInput = (EditText) findViewById(R.id.nameInput);
        txtName = (TextView) findViewById(R.id.txtName);
        txtDifficulty = (TextView) findViewById(R.id.txtDificultad);
        txtSettings = (TextView) findViewById(R.id.txtSettings);

        radioButton1 = (RadioButton) findViewById(R.id.radiob1);
        radioButton2 = (RadioButton) findViewById(R.id.radiob2);

        settingsLayout = (LinearLayout) findViewById(R.id.settingsLayout);

        settingsData = new Bundle();
        settingsData.putString("username","none");
        settingsData.putString("seekBar1", "Medio");

        prepareListener();
        prepareListenerRadio();


        // Add listener to elements (add Observer
        //                           to elements Observables)
        checkbox.setOnClickListener(listener);
        radioGroup.setOnCheckedChangeListener(listenerRadio);
        sendButton.setOnClickListener(listener);

    }

    // Menu that we access through the menu button described in res/menu
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu2, menu);
        return true;
    }

    /**
     * Actions that we make when we click on any menu option by button
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.opGame:
                Toast.makeText(this.getApplicationContext(),
                        "menu_game", Toast.LENGTH_SHORT).show();
                callMainActivity();
                return true;
            case R.id.opExit:
                Toast.makeText(this.getApplicationContext(),
                        "menu_sortir", Toast.LENGTH_SHORT).show();
                endProgram();
                return true;
            case R.id.opTutorial:
                Toast.makeText(this.getApplicationContext(),
                        "menu_tutorial", Toast.LENGTH_SHORT).show();
                showTutorial();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Listener for the SeekBar.
     * SeekBar initializes at 0, asking user to move it and choosing a difficulty.
     *
     * When the user moves the SeekBar, the difficulty text will change depending on the SeekBar progress.
     * We introduce the values of the difficulty to our Bundle that we'll later send to the MainActivity (Game).
     */
    SeekBar.OnSeekBarChangeListener listenner = new SeekBar.OnSeekBarChangeListener() {
        public void onStopTrackingTouch(SeekBar arg0) {

            if (arg0.getProgress() < 25){
                textSeekBar.setText("Fácil");
                settingsData.putString("seekBar1", "Fácil");
            }else if (arg0.getProgress() < 75) {
                textSeekBar.setText("Medio");
                settingsData.putString("seekBar1", "Medio");
            } else if (arg0.getProgress() < 100) {
                textSeekBar.setText("Difícil");
                settingsData.putString("seekBar1", "Dificil");
            }
        }

        public void onStartTrackingTouch(SeekBar arg0) {
        }

        public void onProgressChanged(SeekBar arg0, int progress, boolean arg2) {
            // Progress is the value of SeekBar
            // Modify values of progressBar depending of value of SeekBar
            if (progress < 25) {
                textSeekBar.setText("Fácil");
                settingsData.putString("seekBar1", "Fácil");
            } else if (progress < 75) {
                textSeekBar.setText("Medio");
                settingsData.putString("seekBar1", "Medio");
            } else if (progress < 100) {
                textSeekBar.setText("Difícil");
                settingsData.putString("seekBar1", "Dificil");
            }
        }
    };

    public void controlSeekBar() {

        difficultySeekBar = (SeekBar) findViewById(R.id.seekBar1);
        // Max value of SeekBar
        difficultySeekBar.setMax(100);
        // We increment SeekBar 1 by 1
        difficultySeekBar.setProgress(1);

        // Init text to 100
        textSeekBar = (TextView) findViewById(R.id.dificultadValue);
        textSeekBar.setText("Desliza para seleccionar dificultad");

        // Set Listenner to Seekbar
        difficultySeekBar.setOnSeekBarChangeListener(listenner);
    }

    /**
     * Prepare listener to RadioGroup
     */
    private void prepareListenerRadio() {
        listenerRadio = new RadioGroup.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(RadioGroup group
                    , int checkedId) {
                switch (checkedId) {
                    case R.id.radiob1:
                        showMessage("Fondo 1 selected");
                        setTextColorBlack();
                        settingsLayout.setBackgroundResource(R.drawable.background_mario);
                        settingsData.putInt("background", 1);
                        break;
                    case R.id.radiob2:
                        showMessage("Fondo 2 selected");
                        setTextColorWhite();
                        settingsLayout.setBackgroundResource(R.drawable.background_mario2);
                        settingsData.putInt("background", 2);
                        break;
                }
            }
        };
    }

    /**
     * Prepare Listener to events
     */
    private void prepareListener() {
        listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.checkBox:
                        if(checkbox.isChecked()) {
                            showMessage("MUSIC ON");
                            startService(new Intent(Settings.this,
                                    Music.class));
                        }else{
                            showMessage("MUSIC OFF");
                            stopService(new Intent(Settings.this,
                                    Music.class));
                        }
                        break;
                    case R.id.sendButton:
                        callMainActivity();
                        break;
                }
            }
        };

    }

    private void showMessage(String msg) {
        Toast.makeText(getApplicationContext(), msg,
                Toast.LENGTH_LONG).show();
    }

    /**
     * We get the data of the Bundle, and we crete an intent.
     *
     * Then we start the MainActivity with the data in our bundle so we can later load it.
     */
    public void callMainActivity() {
        String name = String.valueOf(txtInput.getText());
        settingsData.putString("username","JUGADOR: " + name);
        Intent intent = new Intent(this, MainActivity.class);
        // set Bundle to intent
        intent.putExtras(settingsData);
        startActivity(intent);
        stopCrono();
        initializeCrono();
    }

    /**
     * Starts the service
     */
    private void initializeCrono() {
        Intent service = new Intent(this, Crono.class);
        startService(service);
    }

    private void stopCrono() {
        Intent service = new Intent(this, Crono.class);
        stopService(service);
    }

    //Shows to the user a PDF
    public void showTutorial() {
        Intent intent1 = new Intent(Intent.ACTION_VIEW);
        intent1.setDataAndType(Uri.parse("http://commonsware.com/Android/excerpt.pdf"), "application/pdf");
        intent1.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivity(intent1);
    }

    //Sets TextViews of the layout to white color.
    public void setTextColorWhite () {
        textSeekBar.setTextColor(getResources().getColor(R.color.colorWhite));
        txtName.setTextColor(getResources().getColor(R.color.colorWhite));
        txtDifficulty.setTextColor(getResources().getColor(R.color.colorWhite));
        checkbox.setTextColor(getResources().getColor(R.color.colorWhite));
        radioButton1.setTextColor(getResources().getColor(R.color.colorWhite));
        radioButton2.setTextColor(getResources().getColor(R.color.colorWhite));
        txtSettings.setTextColor(getResources().getColor(R.color.colorWhite));
        txtInput.setTextColor(getResources().getColor(R.color.colorWhite));
    }

    //Sets TextViews of the layout to black color.
    public void setTextColorBlack () {
        textSeekBar.setTextColor(getResources().getColor(R.color.colorBlack));
        txtName.setTextColor(getResources().getColor(R.color.colorBlack));
        txtDifficulty.setTextColor(getResources().getColor(R.color.colorBlack));
        checkbox.setTextColor(getResources().getColor(R.color.colorBlack));
        radioButton1.setTextColor(getResources().getColor(R.color.colorBlack));
        radioButton2.setTextColor(getResources().getColor(R.color.colorBlack));
        txtSettings.setTextColor(getResources().getColor(R.color.colorBlack));
        txtInput.setTextColor(getResources().getColor(R.color.colorBlack));
    }


    //Method that ends the program.
    public void endProgram() {
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Confirmación Salida");
        alert.setMessage("Está seguro que desea salir?");
        alert.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent homeIntent = new Intent(Intent.ACTION_MAIN);
                homeIntent.addCategory(Intent.CATEGORY_HOME);
                homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(homeIntent);
            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialogInterface.cancel();
            }
        });
        alert.show();
    }

}
