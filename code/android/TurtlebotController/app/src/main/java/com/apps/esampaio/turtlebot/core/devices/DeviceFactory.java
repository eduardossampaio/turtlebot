package com.apps.esampaio.turtlebot.core.devices;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;

import com.apps.esampaio.turtlebot.core.connection.bluetooth.BluetoothUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by eduardo on 27/08/2016.
 */

public class DeviceFactory {
    public static List<Device> getDevices() {
        List<Device> devices =
                //stubDevices();
                new ArrayList<>();
        devices.addAll(BluetoothUtils.getBluetoothParedDevices());
        return devices;
    }



    private static List<Device> stubDevices(){
        List<Device> devices = new ArrayList<Device>();
        devices.add(new Device("Turtlebot","12345"));
        return devices;
    }
}
