using UnityEngine;

public class UnityAndroidVibratorBridge {
    private static AndroidJavaObject VibratorInstance = null;


    public static void Initialize() {
#if UNITY_ANDROID && !UNITY_EDITOR
        using (AndroidJavaClass activityClass = new AndroidJavaClass("com.unity3d.player.UnityPlayer")) {
            using (AndroidJavaObject activityContext = activityClass.GetStatic<AndroidJavaObject>("currentActivity")) {
                using (AndroidJavaClass pluginClass = new AndroidJavaClass("com.mykaelos.unityandroidvibrator.Vibrator")) {
                    if (pluginClass != null) {
                        VibratorInstance = pluginClass.CallStatic<AndroidJavaObject>("getInstance");
                        VibratorInstance.Call("setContext", activityContext);
                    }
                }
            }
        }

        if (VibratorInstance == null) {
            Debug.Log("UnityAndroidVibratorBridge: Failed to setup the UnityAndroidVibrator.");
        }
#endif
    }

    public static bool IsInitialized() {
        return VibratorInstance != null;
    }

    public static bool HasVibrator() {
        return IsInitialized() && VibratorInstance.Call<bool>("hasVibrator");
    }

    public static void Vibrate(long duration) {
        if (IsInitialized()) {
            VibratorInstance.Call("vibrate", duration);
        }
    }

    public static void Vibrate(long[] pattern, int repeat) {
        if (IsInitialized()) {
            VibratorInstance.Call("vibrate", pattern, repeat);
        }
    }
    
    public static void Cancel() {
        if (IsInitialized()) {
            VibratorInstance.Call("cancel");
        }
    }
}
