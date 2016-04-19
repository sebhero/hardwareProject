package com.hardware.model;

import com.hardware.gui.Clock;

import java.util.TimerTask;

/**
 * Created by Johnatan on 2016-04-15.
 */
public class ClockTimer extends TimerTask implements Runnable {
    private Clock clock;
    public ClockTimer(Clock clock){
        this.clock = clock;
    }
    @Override
    public void run() {
        clock.updateTime();
    }
}
