package com.apps.esampaio.turtlebot.core.connection.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

import com.apps.esampaio.turtlebot.core.connection.Connection;
import com.apps.esampaio.turtlebot.core.devices.Device;
import com.apps.esampaio.turtlebot.core.devices.DeviceBluetooth;
import com.apps.esampaio.turtlebot.core.log.Log;

import java.io.IOException;
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

        connected = true;
    }

    @Override
    public void disconnect() throws Exception {
        outputStream.close();
        inputStream.close();
        socket.close();
        connected = false;
    }

    @Override
    public boolean isConnected() {
        return connected;
    }

    @Override
    public void send(byte[] data) throws Exception {
        try {
            outputStream.write(data);
        } catch (IOException e) {
            Log.error("Error while sending: ", e);
            connected = false;
        }
    }

    @Override
    public byte[] receive() throws Exception {
        try {

            byte[] buffer = new byte[8];
            for(int i=0;i<buffer.length;i++)
                buffer[i]='\0';
            int bytesRead = inputStream.read(buffer);
            return buffer;
        } catch (IOException e) {
            Log.error("Error while receiving: ", e);
            connected = false;
            return null;
        }
    }
}
