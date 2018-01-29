package com.proven.cat.a7ymedio;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;


public class PhoneStatus extends BroadcastReceiver {

    /**
     * Gets an action through the intent. If the action equals to "AIRPLANE_MODE", we will show a toast message.
     */
    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();

        if(action.equals("android.intent.action.AIRPLANE_MODE"))
            Toast.makeText(context, "ATTENTION: \n" + action, Toast.LENGTH_LONG).show();

    }

}
