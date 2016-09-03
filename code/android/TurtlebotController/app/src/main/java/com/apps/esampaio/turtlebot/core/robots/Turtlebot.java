package com.apps.esampaio.turtlebot.core.robots;



import com.apps.esampaio.turtlebot.core.connection.Connection;
import com.apps.esampaio.turtlebot.core.log.Log;

/**
 * Created by eduardo on 28/08/2016.
 */

public class Turtlebot {

    private Connection connection;

    private class Command{
        private String command;
        private String response;

        private Command(String command,String response){
            this.command = command;
            this.response = response;
        }
    }
    private Thread receiveThread;

    //TODO ver das configurações
    private Command commandForward          = new Command("f","F");
    private Command commandBackward         = new Command("b","B");
    private Command commandLeft             = new Command("l","L");
    private Command commandRight            = new Command("r","R");
    private Command commandLedsOn           = new Command("o","O");
    private Command commandLedsOff          = new Command("v","V");
    private Command commandDollHeadLeft     = new Command("q","Q");
    private Command commandDollHeadRight    = new Command("e","E");

    private Command lasCommand;

    public Turtlebot(final Connection connection){
        if(connection!= null) {
            this.connection = connection;
            receiveThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (connection.isConnected()) {
                            receiveCommands();
                        }
                    }catch (Exception e){
                        Log.error("Error while receiving commands",e);
                    }
                }
            });
            receiveThread.start();
        }
    }
    private void receiveCommands(){
        try {
            byte [] data = this.connection.receive();
            if(data != null && data.length>0) {
                StringBuilder sb = new StringBuilder();
                for (byte b : data) {
                    sb.append(String.format("%02X ", b));
                }
                Log.info("received: [" + data.length + "]: " + sb.toString());
                if(lasCommand != null && lasCommand.response.charAt(0)==data[0])
                    lasCommand = null;
            }
        }catch (Exception e){
            Log.error("Error while receiving ",e);
        }
    }
    public void shutdown() throws Exception{
        if(this.connection!= null){
            this.connection.disconnect();
        }
    }

    public void forward() throws Exception{
        sendCommand(commandForward);
    }
    public void backward() throws Exception{
        sendCommand(commandBackward);
    }
    public void left() throws Exception{
        sendCommand(commandLeft);
    }
    public void right()throws Exception{
        sendCommand(commandRight);
    }
    public void headLeft()throws Exception{
        sendCommand(commandDollHeadLeft);
    }
    public void headRight()throws Exception{
        sendCommand(commandDollHeadRight);
    }
    public void ledsOn() throws Exception{
        sendCommand(commandLedsOn);
    }
    public void ledsOff() throws Exception{
        sendCommand(commandLedsOff);
    }

    private void sendCommand(Command command) throws Exception{
        if(connection!= null) {
            if(lasCommand== null) {
                lasCommand = command;
                connection.send(lasCommand.command.getBytes());
            }
        }
    }


}
