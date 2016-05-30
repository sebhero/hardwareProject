package com.hardware.model;

import com.hardware.gui.MainLayout;
import java.util.TimerTask;

/**
 * @author Johnatan on 2016-04-15.
 */
public class WelcomeReset extends TimerTask implements Runnable{

    private MainLayout layout;

    public WelcomeReset(MainLayout  mainLayout) {
        this.layout = mainLayout;
    }

    @Override
    public void run() {
        layout.resetWelcome();
    }
}
