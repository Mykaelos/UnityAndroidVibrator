package com.mykaelos.unityandroidvibrator;


import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class Vibrator {
    private static final String TAG = Vibrator.class.getSimpleName();
    private static Vibrator instance;
    private Context context;
    private android.os.Vibrator vibratorService;

    private Vibrator() {
        instance = this;
    }

    public static Vibrator getInstance() {
        if (instance == null) {
            instance = new Vibrator();
        }
        return instance;
    }

    public void setContext(Context context) {
        Log.i(TAG, "setContext");
        this.context = context;
        vibratorService = (android.os.Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
    }

    public boolean hasVibrator() {
        return vibratorService.hasVibrator();
    }

    public void vibrate(long duration) {
        Log.i(TAG, "vibrate for " + duration);
        vibratorService.vibrate(duration);
    }

    public void vibrate(long[] pattern, int repeat) {
        vibratorService.vibrate(pattern, repeat);
    }

    public void toast(String message){
        Log.i(TAG, "toast for " + message);

        final String tempString = message;

        ((Activity)context).runOnUiThread(new Runnable() {
            public void run() {
                Log.i(TAG, "Toast.makeText");
                Toast.makeText(context, tempString, Toast.LENGTH_LONG).show();
            }
        });
    }
}
