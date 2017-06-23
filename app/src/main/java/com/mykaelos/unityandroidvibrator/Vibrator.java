package com.mykaelos.unityandroidvibrator;


import android.content.Context;
import android.util.Log;

@SuppressWarnings({"StaticFieldLeak", "WeakerAccess", "unused"})
public class Vibrator {
    private static final String TAG = Vibrator.class.getSimpleName();

    private static Vibrator instance;
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
        Log.d(TAG, "setContext");
        vibratorService = (android.os.Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
    }

    public boolean hasVibrator() {
        Log.d(TAG, "hasVibrator: " + vibratorService.hasVibrator());
        return vibratorService.hasVibrator();
    }

    public void vibrate(long duration) {
        Log.d(TAG, "vibrate for " + duration);
        vibratorService.vibrate(duration);
    }

    public void vibrate(long[] pattern, int repeat) {
        Log.d(TAG, "vibrate repeat: " + repeat);
        vibratorService.vibrate(pattern, repeat);
    }

    public void cancel() {
        Log.d(TAG, "vibrate cancel");
        vibratorService.cancel();
    }
}
