package com.apps.esampaio.turtlebot.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.apps.esampaio.turtlebot.R;
import com.apps.esampaio.turtlebot.core.devices.Device;
import com.apps.esampaio.turtlebot.core.devices.DeviceBluetooth;
import com.apps.esampaio.turtlebot.core.robots.Turtlebot;

import java.util.List;
import java.util.Map;

/**
 * Created by eduardo on 31/08/2016.
 */

public class DeviceItemListAdapter extends BaseAdapter {
    private Context context;
    private List<Device> devices;


    public DeviceItemListAdapter(Context context, List<Device> devices){
        this.context = context;
        this.devices = devices;
    }

    @Override
    public int getCount() {
        return devices.size();
    }

    @Override
    public Object getItem(int position) {
        return devices.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.device_item,null,false);
        ImageView icon  = (ImageView) view.findViewById(R.id.item_list_icon);
        TextView name = (TextView) view.findViewById(R.id.item_list_name);
        TextView addredd = (TextView) view.findViewById(R.id.item_list_address);

        Device device = devices.get(position);
        if(device instanceof DeviceBluetooth)
            icon.setImageResource(R.mipmap.ic_bluetooth);
        name.setText(device.getName());
        addredd.setText(device.getAddress());

        return view;
    }
}
