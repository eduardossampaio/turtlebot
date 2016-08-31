package com.apps.esampaio.turtlebot.view.acttivities.listeners;



import com.apps.esampaio.turtlebot.core.log.Log;
import com.apps.esampaio.turtlebot.core.robots.Turtlebot;
import com.apps.esampaio.turtlebot.core.util.Counter;
import com.jmedeisis.bugstick.JoystickListener;

/**
 * Created by eduardo on 31/08/2016.
 */

public class MovementJoystickListener implements JoystickListener {
    private static final int DEGRESS_RANGE = 20;
    private Turtlebot turtlebot;

    private Counter counter = new Counter();
    private int direction;
    private boolean isToMove;

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
        } else if (degrees >= 180 - DEGRESS_RANGE || (degrees >= -180 + DEGRESS_RANGE && degrees <= -180 - DEGRESS_RANGE)) {
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
    private boolean canMove(){
        if ( counter.getCount() >= 700){
            counter.restartCount();
            return true;
        }
        return false;
    }
    private void forward(){
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
    private void backward(){
        if(canMove()) {
            Log.info("BACKWARD");
            try {
                turtlebot.backward();
            }catch (Exception e){
                Log.error("Error",e);
            }
        }
    }
    private void left(){
        if(canMove()) {
            Log.info( "LEFT");
            try {
                turtlebot.left();
            }catch (Exception e){
                Log.error("Error",e);
            }
        }
    }
    private void right(){
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
