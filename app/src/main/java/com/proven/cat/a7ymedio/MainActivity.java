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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private LinearLayout mainLayout;
    private LinearLayout bottomdata;
    private Bundle bundle;
    private ImageView imageView[] = new ImageView[8];
    private TextView textMovements;
    private TextView textPoints;
    private TextView textUsername;
    private TextView textDificulty;
    private TextView textDificulty2;
    OnClickListener listener;

    Game model;

    private TextView textCrono;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //We initialize model
        model = new Game();

        model.addCards();

        textCrono = (TextView) findViewById(R.id.cronometro);
        Crono.setUpdateListener(this);

        initializeCrono();

        //We initialize elements
        initiateElements();

        //We get the bundle, with the data from the previous activity (Settings).
        bundle = this.getIntent().getExtras();

        if (bundle != null) {
            String username = bundle.getString("username");
            textUsername = (TextView) findViewById(R.id.textUserName);
            textUsername.setText(username);

            String seekBar1Dificultad = bundle.getString("seekBar1");
            textDificulty = (TextView) findViewById(R.id.valorDificultad);
            textDificulty.setText(seekBar1Dificultad);

            int background = bundle.getInt("background");
            switch (background) {
                case 1:
                    mainLayout.setBackgroundResource(R.drawable.background_mario);
                    break;
                case 2:
                    mainLayout.setBackgroundResource(R.drawable.background_mario2);
                    setSecondBackground();
                    break;
            }
        }
        //Init listener
        prepareListener();
        //Set element to Listener
        setElementListener();
    }

    /**
     * Initializes the service
     */
    private void initializeCrono() {
        Intent service = new Intent(this, Crono.class);
        startService(service);
    }

    /**
     * Ends the service
     */
    private void stopCrono() {
        Intent service = new Intent(this, Crono.class);
        stopService(service);
        Toast.makeText(getApplicationContext(), "la cuenta final es " + textCrono.getText(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        //Before closing the application the service is stopped since
        //the service is associated with this activity and needs to use the methods it implements
        stopCrono();
        super.onDestroy();
    }

    /**
     * Update in the UI the time that is timed, the call is made in the service
     *
     * @param time
     */
    public void updateCrono(double time) {
        textCrono.setText(String.format("%.2f", time) + "s");
    }


    // Menu that we access through the menu button described in res/menu
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }


    private void initiateElements() {

        imageView[0] = (ImageView) findViewById(R.id.imgPos0);
        imageView[1] = (ImageView) findViewById(R.id.imgPos1);
        imageView[2] = (ImageView) findViewById(R.id.imgPos2);
        imageView[3] = (ImageView) findViewById(R.id.imgPos3);
        imageView[4] = (ImageView) findViewById(R.id.imgPos4);
        imageView[5] = (ImageView) findViewById(R.id.imgPos5);
        imageView[6] = (ImageView) findViewById(R.id.imgPos6);
        imageView[7] = (ImageView) findViewById(R.id.imgPos7);
        textMovements = (TextView) findViewById(R.id.textMovements);
        textPoints = (TextView) findViewById(R.id.textPoints);
        mainLayout = (LinearLayout) findViewById(R.id.mainLayout);
        bottomdata = (LinearLayout) findViewById(R.id.bottomdata);
        textDificulty2 = (TextView) findViewById(R.id.textDificultad);

    }


    /**
     * Actions made when we click any of the options in the menus
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.opSettings:
                Toast.makeText(this.getApplicationContext(),
                        "menu_settings", Toast.LENGTH_SHORT).show();
                cridaSettingsActivity();
                return true;
            case R.id.opExit:
                Toast.makeText(this.getApplicationContext(),
                        "menu_sortir", Toast.LENGTH_SHORT).show();
                exitApp();
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

    private void prepareListener() {
        listener = new OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.imgPos0:
                        model.changeCard(0);
                        showCard(model.getCard(0), imageView[0]);
                        model.incrementCounter();
                        break;
                    case R.id.imgPos1:
                        model.changeCard(1);
                        showCard(model.getCard(1), imageView[1]);
                        model.incrementCounter();
                        break;
                    case R.id.imgPos2:
                        model.changeCard(2);
                        showCard(model.getCard(2), imageView[2]);
                        model.incrementCounter();
                        break;
                    case R.id.imgPos3:
                        model.changeCard(3);
                        showCard(model.getCard(3), imageView[3]);
                        model.incrementCounter();
                        break;
                    case R.id.imgPos4:
                        model.changeCard(4);
                        showCard(model.getCard(4), imageView[4]);
                        model.incrementCounter();
                        break;
                    case R.id.imgPos5:
                        model.changeCard(5);
                        showCard(model.getCard(5), imageView[5]);
                        model.incrementCounter();
                        break;
                    case R.id.imgPos6:
                        model.changeCard(6);
                        showCard(model.getCard(6), imageView[6]);
                        model.incrementCounter();
                        break;
                    case R.id.imgPos7:
                        model.changeCard(7);
                        showCard(model.getCard(7), imageView[7]);
                        model.incrementCounter();
                        break;
                }
                validateResult();
                if (model.valueTotalCards() > 7.5) {
                    stopCrono();

                    finish("HAS PERDIDO!");
                } else if (model.valueTotalCards() == 7.5) {
                    stopCrono();

                    finish("HAS GANADO!");
                }
            }
        };
    }


    private void setElementListener() {
        imageView[0].setOnClickListener(listener);
        imageView[1].setOnClickListener(listener);
        imageView[2].setOnClickListener(listener);
        imageView[3].setOnClickListener(listener);
        imageView[4].setOnClickListener(listener);
        imageView[5].setOnClickListener(listener);
        imageView[6].setOnClickListener(listener);
        imageView[7].setOnClickListener(listener);
    }

    /**
     * Shows the image corresponding to the card value
     */
    private void showCard(Card card, ImageView imageViewCarta) {
        if (card.isVisible()) {
            //Shows value
            if (card.getValue() == 0.5) {
                imageViewCarta.setImageResource(R.drawable.half);
            } else if (card.getValue() == 1) {
                imageViewCarta.setImageResource(R.drawable.one);
            } else if (card.getValue() == 2) {
                imageViewCarta.setImageResource(R.drawable.two);
            } else if (card.getValue() == 3) {
                imageViewCarta.setImageResource(R.drawable.three);
            } else if (card.getValue() == 4) {
                imageViewCarta.setImageResource(R.drawable.four);
            } else if (card.getValue() == 5) {
                imageViewCarta.setImageResource(R.drawable.five);
            } else if (card.getValue() == 6) {
                imageViewCarta.setImageResource(R.drawable.one);
            } else if (card.getValue() == 7) {
                imageViewCarta.setImageResource(R.drawable.three);
            }
        } else {
            //Back card
            imageViewCarta.setImageResource(R.drawable.back);
        }
    }

    //Shows the current stats on the view
    private void validateResult() {
        double total = model.valueTotalCards();

        textPoints.setText("Puntos: " + total);

        textMovements.setText("Movimientos realizados: " + model.getCounter());
    }

    /**
     * Method we call when we finish the game.
     * We show win/lose message, and in the AlertDialog, we show the end data.
     * The movements used, the total points, and two options.
     * User can choose between exiting the app, or starting a new game.
     *
     * In case exit is pressed, we take the user back to home screen.
     * If play again is pressed, we reset the scores, the counter, and a new game is started.
     *
     * @param msg shows  won/lost message
     */
    private void finish(String msg) {
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(msg + "\nTIEMPO EMPLEADO: " + textCrono.getText());
        alert.setMessage("Movimientos realizados: " + model.getCounter() + "\nPuntos: " + model.valueTotalCards() + "\n\nQue desea hacer ahora?");
        alert.setPositiveButton("VOLVER A JUGAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                model.resetCounter();
                model.resetTotalValue();
                initializeCrono();
                for (int j = 0; j < imageView.length; j++) {
                    imageView[j].setImageResource(R.drawable.back);
                }
            }
        });
        alert.setNegativeButton("SALIR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                stopService(new Intent(MainActivity.this,
                        Music.class));
                Intent homeIntent = new Intent(Intent.ACTION_MAIN);
                homeIntent.addCategory(Intent.CATEGORY_HOME);
                homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(homeIntent);
            }
        });
        alert.show();
    }

    /**
     * Method used in case user changes the background to second option.
     * We change the TextViews of the second background layout to white.
     */
    private void setSecondBackground() {
        bottomdata.setPadding(0, 0, 0, 350);
        textCrono.setTextColor(getResources().getColor(R.color.colorWhite));
        textDificulty.setTextColor(getResources().getColor(R.color.colorWhite));
        textUsername.setTextColor(getResources().getColor(R.color.colorWhite));
        textMovements.setTextColor(getResources().getColor(R.color.colorWhite));
        textPoints.setTextColor(getResources().getColor(R.color.colorWhite));
        textDificulty2.setTextColor(getResources().getColor(R.color.colorWhite));
    }

    //Calls for Settings activity
    public void cridaSettingsActivity() {
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }

    //Shows to the user a PDF
    public void showTutorial() {
        Intent intent1 = new Intent(Intent.ACTION_VIEW);
        intent1.setDataAndType(Uri.parse("http://commonsware.com/Android/excerpt.pdf"), "application/pdf");
        intent1.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivity(intent1);
    }

    //Method called when exit app button in menu is pressed.
    private void exitApp() {

        endProgram("Confirmación Salida");
    }

    //Method that ends the program.
    public void endProgram(String msg) {
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(msg);
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