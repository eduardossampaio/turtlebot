package com.apps.esampaio.turtlebot.core.devices;

import android.bluetooth.BluetoothDevice;

/**
 * Created by eduardo on 31/08/2016.
 */

public class DeviceBluetooth extends Device {
    BluetoothDevice bluetoothDevice;
    public DeviceBluetooth(String name, String address, BluetoothDevice device) {
        super(name, address);
        this.bluetoothDevice = device;
    }

    public DeviceBluetooth() {
    }

    public BluetoothDevice getBluetoothDevice() {
        return bluetoothDevice;
    }
}
