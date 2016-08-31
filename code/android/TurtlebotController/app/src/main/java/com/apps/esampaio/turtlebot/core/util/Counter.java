package com.apps.esampaio.turtlebot.core.util;

/**
 * Created by eduardo on 28/08/2016.
 */

public class Counter {
    private long startTíme = 0;

    public void startCount(){
        startTíme = System.currentTimeMillis();
    }
    public void stopCount(){
        startTíme = 0;
    }
    public long getCount(){
        if(startTíme == 0)
            return 0;
        return System.currentTimeMillis() - startTíme;
    }

    public void restartCount() {
        startCount();
    }
}
