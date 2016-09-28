package com.example.adi.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.adi.myapplication.Activites.NavigationDrawer_Activity;

/**
 * Created by Adi on 28/09/2016.
 */
public class ScreenReceiver extends BroadcastReceiver {
    public static boolean wasScreenOn = true;

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
            // do whatever you need to do here
            Log.d("TAG", "onReceive: sleeping working right");

            wasScreenOn = false;
        } else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
            // and do whatever you need to do here
            Log.d("TAG", "onReceive22: sleeping working ");
            wasScreenOn = true;
        }
        else if (intent.getAction().equals(Intent.ACTION_POWER_CONNECTED)) {
            // and do whatever you need to do here
            Log.d("TAG", "onReceive22: sleeping working ");
            wasScreenOn = true;
        }
        else if (intent.getAction().equals(Intent.ACTION_POWER_DISCONNECTED)) {
            // and do whatever you need to do here
            Toast.makeText(context, "disconnected", Toast.LENGTH_LONG).show();
            wasScreenOn = true;
        }
    }
}
