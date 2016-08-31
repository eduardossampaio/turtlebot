package com.apps.esampaio.turtlebot.core.connection.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

import com.apps.esampaio.turtlebot.core.connection.Connection;
import com.apps.esampaio.turtlebot.core.devices.Device;
import com.apps.esampaio.turtlebot.core.devices.DeviceBluetooth;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

/**
 * Created by eduardo on 27/08/2016.
 */

public class BluetoothConnection implements Connection {

    private BluetoothSocket socket;
    private InputStream inputStream;
    private OutputStream outputStream;

    private boolean connected;

    @Override
    public void connect(Device device) throws Exception {
        DeviceBluetooth deviceBluetooth = (DeviceBluetooth) device;
        BluetoothDevice bluetoothDevice = deviceBluetooth.getBluetoothDevice();
        UUID uuid = bluetoothDevice.getUuids()[0].getUuid();

        socket = bluetoothDevice.createRfcommSocketToServiceRecord(uuid);
        socket.connect();

        inputStream = socket.getInputStream();
        outputStream = socket.getOutputStream();
    }

    @Override
    public void disconnect() throws Exception{
        outputStream.close();
        inputStream.close();
        socket.close();
        connected = false;
    }

    @Override
    public boolean isConnected() {
        return false;
    }

    @Override
    public void send(byte[] data) throws Exception {
        outputStream.write(data);
    }

    @Override
    public byte[] receive() throws Exception {
        byte [] buffer = new byte[64];
        inputStream.read(buffer);
        return buffer;
    }
}
