package com.hardware.model;

import com.hardware.gui.MainLayout;
import com.sun.javaws.Main;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

import java.util.TimerTask;

/**
 * Created by Johnatan on 2016-04-15.
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
