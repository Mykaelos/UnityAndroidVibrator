This is an example project on how to make a Unity Android plugin, and it also doubles as a way to handle vibrations for Androids.

:heavy_exclamation_mark: You should use the [AndroidVibrateManager](https://github.com/Mykaelos/MykaelosUnityLibrary/blob/master/Android/AndroidVibrateManager.cs) for vibrations instead of this, because it calls Android's vibrate class directly instead of using a jar. I'm leaving this project around as an example on how to make an Android Plugin.


### Build Jar
Run the buildJar.bat or run gradlew jar. This will make the jar inside of the app/build/libs/ folder.


### Copy the Files into your Unity Project
Copy the UnityAndroidVibrator.jar into the Plugins/Android/ folder of your project.
Copy the UnityAndroidVibratorBridge.cs into the Scripts/ folder of your project.


### Android Setup
You need to include the following permission in your AndroidManifest.xml

```
<uses-permission android:name="android.permission.VIBRATE" />
```

You can either copy the Unity built manifest from Temp/StatingArea/AndroidManifest.xml into your Plugins/Android/ folder, and add the VIBRATE permission, or you can trick Unity to add it for you by including Handheld.Vibrate() in your code somewhere. If you don't want Unity to actually run Handheld.Vibrate(), you can block it with an if statement that never runs:

```
bool alwaysFalse = false;
if (alwaysFalse) {
    Handheld.Vibrate();
}
```

**Note:** `if (false)` wont work because Unity will see it as unreachable code, and not include it in the project, and you won't get the permission automatically added for you.

You could also try to merge in the permission yourself with a PostProcessBuild Step for Android [like this one](https://github.com/gree/unity-webview/blob/master/plugins/Android/Editor/UnityWebViewPostprocessBuild.cs).


### Use the Code
Make sure to call `UnityAndroidVibratorBridge.Initialize()` early on, before you need to trigger the vibrator.

Then call `UnityAndroidVibratorBridge.Vibrate(500)` to vibrate for 500 milliseconds.
