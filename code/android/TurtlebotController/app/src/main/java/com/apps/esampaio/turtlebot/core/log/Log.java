package com.apps.esampaio.turtlebot.core.log;

/**
 * Created by eduardo on 31/08/2016.
 */

public class Log {
    private static final String TAG = "TURTLEBOT";

    public static void info(String message){
        android.util.Log.i(TAG,message);
    }
    public static void debug(String message){
        android.util.Log.d(TAG,message);
    }
    public static void error(String message){
        android.util.Log.e(TAG,message);
    }
    public static void error(String message,Exception e){
        android.util.Log.e(TAG,message,e);
    }

}

