package com.apps.esampaio.turtlebot.view.acttivities.listeners;



import com.apps.esampaio.turtlebot.core.log.Log;
import com.apps.esampaio.turtlebot.core.robots.Turtlebot;
import com.apps.esampaio.turtlebot.core.util.Counter;
import com.jmedeisis.bugstick.JoystickListener;

/**
 * Created by eduardo on 31/08/2016.
 */

public class MovementJoystickListener implements JoystickListener {

    protected static int DEGRESS_RANGE = 20;
    protected Turtlebot turtlebot;

    protected Counter counter = new Counter();
    protected int direction;
    protected boolean isToMove;

    @Override
    public void onDown() {
        Log.info("onDown");
        counter.startCount();
    }

    @Override
    public void onDrag(float degrees, float offset) {
        if (offset < 1) {
            direction = 0;
            return;
        }
        if (degrees >= 90 - DEGRESS_RANGE && degrees <= 90 + DEGRESS_RANGE) {
            direction =1;
        } else if (degrees >= -90 - DEGRESS_RANGE && degrees <= -90 + DEGRESS_RANGE) {
            direction =2;
        } else if (degrees >= 0 - DEGRESS_RANGE && degrees <= 0 + DEGRESS_RANGE) {
            direction =3;
        } else if (degrees >= 180 - DEGRESS_RANGE || (degrees <= -180 + DEGRESS_RANGE)) {
            direction =4;
        }
        if(isToMove == false) {
            isToMove = true;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (isToMove) {
                        if (direction == 1) {
                            forward();
                        } else if (direction == 2) {
                            backward();
                        } else if (direction == 3) {
                            right();
                        } else if (direction == 4) {
                            left();
                        }
                    }
                }
            }).start();
        }
    }

    public void setTurtlebot(Turtlebot turtlebot) {
        this.turtlebot = turtlebot;
    }

    @Override
    public void onUp() {
        Log.info("onUp");
        counter.stopCount();
        isToMove = false;
    }
    protected boolean canMove(){
        if ( counter.getCount() >= 50){
            counter.restartCount();
            return true;
        }
        return false;
    }
    protected void forward(){
        if(canMove()) {
            Log.info("FORWARD");
            if(turtlebot !=null) {
                try {
                    turtlebot.forward();
                }catch (Exception e){
                    Log.error("Error",e);
                }
            }
        }
    }
    protected void backward(){
        if(canMove()) {
            Log.info("BACKWARD");
            try {
                turtlebot.backward();
            }catch (Exception e){
                Log.error("Error",e);
            }
        }
    }
    protected void left(){
        if(canMove()) {
            Log.info( "LEFT");
            try {
                turtlebot.left();
            }catch (Exception e){
                Log.error("Error",e);
            }
        }
    }
    protected void right(){
        if(canMove()) {
            Log.info("RIGHT");
            try {
                turtlebot.right();
            }catch (Exception e){
                Log.error("Error",e);
            }
        }
    }

}
