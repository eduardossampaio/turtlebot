package com.apps.esampaio.turtlebot.view.dialogs;

import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.apps.esampaio.turtlebot.R;
import com.apps.esampaio.turtlebot.core.devices.Device;
import com.apps.esampaio.turtlebot.view.adapter.DeviceItemListAdapter;

import java.util.List;



/**
 * Created by eduardo on 28/08/2016.
 */

public class ListDeviceDialog {
    private Activity activity;
    private List<Device> devices;
    private AlertDialog.Builder builder;
    private ListView list;
    private AlertDialog dialog;

    public ListDeviceDialog(Activity activity,List<Device> devices){
        this.activity = activity;
        this.devices = devices;
    }

    public void create(){
        View view =  activity.getLayoutInflater().inflate(R.layout.dialog_list_devices,null);
        list = (ListView) view.findViewById(R.id.dialog_list_devices_list);
        list.setAdapter(new DeviceItemListAdapter(activity,devices));
        builder = new AlertDialog.Builder(activity)
                .setTitle(activity.getString(R.string.dialog_list_device_title))
                .setView(view);
    }

    public void setOnClickListenner(final AdapterView.OnItemClickListener listenner){
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listenner.onItemClick(parent,view,position,id);
                if(dialog != null){
                    dialog.dismiss();
                }
            }
        });
    }
    public void show(){
        dialog = builder.show();
    }
}
