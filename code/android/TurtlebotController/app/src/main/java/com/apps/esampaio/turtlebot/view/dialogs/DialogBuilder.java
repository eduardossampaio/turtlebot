package com.apps.esampaio.turtlebot.view.dialogs;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * Created by eduardo on 28/08/2016.
 */

public class DialogBuilder {
    public static void createSimpleAlertDialog(Context context,String title,String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("Ok", null);
        builder.show();
    }


    public static void createSimpleErrorDialog(Context context,String title,Exception e){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage("Error: "+e.getMessage());
        builder.setPositiveButton("Ok", null);
        builder.show();
    }
}
