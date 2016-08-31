package com.apps.esampaio.turtlebot.core.connection.bluetooth;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;

import com.apps.esampaio.turtlebot.core.devices.Device;
import com.apps.esampaio.turtlebot.core.devices.DeviceBluetooth;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by eduardo on 31/08/2016.
 */

public class BluetoothUtils {
    public static boolean isEnabled(){
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if( mBluetoothAdapter == null)
            throw new UnsupportedOperationException("Device has no bluetooth capabilities");
        return mBluetoothAdapter.isEnabled();
    }

    public static void requestEnableBluetooth(Activity activity){
        Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        activity.startActivityForResult(enableBtIntent, 0);
    }

    public static List<Device> getBluetoothParedDevices() {
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if( mBluetoothAdapter == null)
            throw new UnsupportedOperationException("Device has no bluetooth capabilities");
        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
        List<Device> devicesFound = new ArrayList<Device>();
        if (pairedDevices.size() > 0) {
            for (BluetoothDevice device : pairedDevices) {
                String deviceBTName = device.getName();
                String deviceBTAddress = device.getAddress();
                devicesFound.add(new DeviceBluetooth(deviceBTName, deviceBTAddress,device));
            }
        }
        return devicesFound;
    }

}
