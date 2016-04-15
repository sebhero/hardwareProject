package com.hardware.gui;

import javafx.scene.shape.Line;

/**
 * Created by Johnatan on 2016-04-13.
 */
public class Clock {
    private Line lineMinute = new Line();
    private Line lineHour = new Line();
    MainLayout m;
    public Clock(MainLayout m){
        this.m = m;
        lineMinute.setStartX(105);
        lineMinute.setStartY(105);
        lineHour.setStartX(105);
        lineHour.setStartY(105);
        //hello
    }
    public void clockLine(int hour, int minute){
        //radie 80
        int angleM = (minute * 6);
        int angleH = (hour * 30) + (minute/2);
        double xM = 105 + 72 * Math.cos(((angleM - 90)*Math.PI)/180);
        double xH = 105 + 60 * Math.cos(((angleH - 90)*Math.PI)/180);
        double yM = 105 + 72 * Math.sin(((angleM - 90)*Math.PI)/180);
        double yH = 105 + 60 * Math.sin(((angleH - 90)*Math.PI)/180);
        lineMinute.setEndY(yM);
        lineHour.setEndY(yH);
        lineMinute.setEndX(xM);
        lineHour.setEndX(xH);
        m.getChildren().add(lineMinute);
        m.getChildren().add(lineHour);

    }
}
