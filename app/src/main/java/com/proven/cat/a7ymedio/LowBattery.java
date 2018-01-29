package com.proven.cat.a7ymedio;

/**
 * Created by dmora on 25/01/2018.
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.widget.Toast;


public class LowBattery extends BroadcastReceiver {

    String action;

    @Override
    public void onReceive(Context context, Intent intent) {
        //We get the action from intent
        action = intent.getAction();

        //We create a switch, and depending of the case, we will show a toast message, with the intent action.
        assert action != null;
        switch (action) {
            case "android.intent.action.BATTERY_LOW":
                Toast toast2 =
                        Toast.makeText(context,
                                "ATTENTION: \n" + action, Toast.LENGTH_SHORT);

                toast2.setGravity(Gravity.CENTER | Gravity.LEFT, 0, 0);
                toast2.show();
                break;

            case "android.intent.action.ACTION_POWER_CONNECTED":
                Toast toast3 =
                        Toast.makeText(context,
                                "ATTENTION: \n" + action, Toast.LENGTH_SHORT);

                toast3.setGravity(Gravity.CENTER | Gravity.LEFT, 0, 0);
                toast3.show();
        }

    }

}