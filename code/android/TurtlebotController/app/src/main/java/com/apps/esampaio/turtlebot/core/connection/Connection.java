package com.apps.esampaio.turtlebot.core.connection;

import com.apps.esampaio.turtlebot.core.devices.Device;

/**
 * Created by eduardo on 27/08/2016.
 */

public interface Connection {
    public void connect(Device device) throws Exception;
    public void disconnect() throws Exception;
    public boolean isConnected() throws  Exception;
    public void send(byte [] data) throws Exception;
    public byte[] receive() throws Exception;
}
