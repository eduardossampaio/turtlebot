package com.apps.esampaio.turtlebot.view.acttivities.listeners;

import com.apps.esampaio.turtlebot.core.log.Log;

/**
 * Created by eduardo on 31/08/2016.
 */

public class HeadMovementJoystickListener extends MovementJoystickListener {

    public HeadMovementJoystickListener(){
        DEGRESS_RANGE = 45;
    }

    protected void left(){
        if(canMove()) {
            Log.info( "HEAD LEFT");
            try {
                turtlebot.headLeft();
            }catch (Exception e){
                Log.error("Error",e);
            }
        }
    }
    protected void right(){
        if(canMove()) {
            Log.info("HEAD RIGHT");
            try {
                turtlebot.headRight();
            }catch (Exception e){
                Log.error("Error",e);
            }
        }
    }

    @Override
    protected void backward() {}

    @Override
    protected void forward() {}

    @Override
    protected boolean canMove() {
        if (counter.getCount() >= 50) {
            counter.restartCount();
            return true;
        }
        return false;
    }
}
