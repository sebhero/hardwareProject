package com.hardware.model;

import com.hardware.gui.Clock;

import java.util.TimerTask;

/**
 * @author Johnatan on 2016-04-15.
 * This class handles the timer for the clock
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
