package com.apps.esampaio.turtlebot.core.robots;

import com.apps.esampaio.turtlebot.core.connection.Connection;

/**
 * Created by eduardo on 28/08/2016.
 */

public class Turtlebot {

    private Connection connection;

    //TODO ver das configurações
    private String forwardCommand   = "f";
    private String backwardCommand  = "b";
    private String leftCommand      = "l";
    private String rightCommand     = "r";
    private String ledsOnCommand    = "o";
    private String ledsOffCommand   = "v";


    public Turtlebot(Connection connection){
        if(connection!= null) {
            this.connection = connection;
        }
    }
    public void shutdown() throws Exception{
        if(this.connection!= null){
            this.connection.disconnect();
        }
    }

    public void forward() throws Exception{
        if(connection!= null) {
            connection.send(forwardCommand.getBytes());
        }
    }
    public void backward() throws Exception{
        if(connection!= null) {
            connection.send(backwardCommand.getBytes());
        }
    }
    public void left() throws Exception{
        if(connection!= null) {
            connection.send(leftCommand.getBytes());
        }
    }
    public void right()throws Exception{
        if(connection!= null) {
            connection.send(rightCommand.getBytes());
        }
    }
    public void headLeft(){

    }
    public void headRight(){

    }
    public void ledsOn() throws Exception{
        if(connection!= null) {
            connection.send(ledsOnCommand.getBytes());
        }
    }
    public void ledsOff() throws Exception{
        if(connection!= null) {
            connection.send(ledsOffCommand.getBytes());
        }
    }


}
