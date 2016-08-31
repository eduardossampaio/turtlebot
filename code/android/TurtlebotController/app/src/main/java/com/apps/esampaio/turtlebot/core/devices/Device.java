package com.apps.esampaio.turtlebot.core.devices;

/**
 * Created by eduardo on 27/08/2016.
 */

public class Device {
    private String name;
    private String address;
    public Device(){}

    public Device(String name,String address){
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return name;
    }
}
