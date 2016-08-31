package com.apps.esampaio.turtlebot.core.connection;

import com.apps.esampaio.turtlebot.core.connection.bluetooth.BluetoothConnection;
import com.apps.esampaio.turtlebot.core.devices.Device;
import com.apps.esampaio.turtlebot.core.devices.DeviceBluetooth;

/**
 * Created by eduardo on 31/08/2016.
 */

public class ConnectionFactory {
    public static Connection createConnection(Device device){
        if(device instanceof DeviceBluetooth)
            return new BluetoothConnection();
        else
            return null;
    }
}
