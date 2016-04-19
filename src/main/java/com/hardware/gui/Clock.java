package com.hardware.gui;

import com.hardware.model.ClockTimer;
import javafx.application.Platform;
import javafx.scene.shape.Line;

import java.util.Timer;

/**
 * Created by Johnatan on 2016-04-13.
 */
public class Clock {
    private int hour = 3;
    private int minute = 49;
    private int second = 0;
    private Line lineMinute = new Line();
    private Line lineHour = new Line();
    private Line lineSecond = new Line();
    private Timer timerClock;
    MainLayout m;
    public Clock(MainLayout m){
        this.m = m;
        timerClock = new Timer();
        timerClock.scheduleAtFixedRate(new ClockTimer(this), 10, 1000);
        lineMinute.setStartX(105);
        lineMinute.setStartY(105);
        lineSecond.setStartX(105);
        lineSecond.setStartY(105);
        lineHour.setStartX(105);
        lineHour.setStartY(105);
    }
    public void updateTime(){
        second++;
        if(second==60){
            minute++;
            if(minute==60){
                hour++;
                minute = 0;
            }
            second = 0;
        }
        if(hour == 12){
            hour = 0;
        }
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                clockLine(hour, minute, second);
            }
        });
    }
    public void clockLine(int hour, int minute, int second){
        if(m.getChildren()!=null) {
            m.getChildren().remove(lineMinute);
            m.getChildren().remove(lineHour);
            m.getChildren().remove(lineSecond);
        }
        //radie 80
        int angleM = (minute * 6);
        int angleS = (second * 6);
        int angleH = (hour * 30) + (minute/2);
        double xM = 105 + 72 * Math.cos(((angleM - 90)*Math.PI)/180);
        double xH = 105 + 60 * Math.cos(((angleH - 90)*Math.PI)/180);
        double xS = 105 + 78 * Math.cos(((angleS - 90)*Math.PI)/180);
        double yM = 105 + 72 * Math.sin(((angleM - 90)*Math.PI)/180);
        double yH = 105 + 60 * Math.sin(((angleH - 90)*Math.PI)/180);
        double yS = 105 + 78 * Math.sin(((angleS - 90)*Math.PI)/180);
        lineMinute.setEndY(yM);
        lineHour.setEndY(yH);
        lineSecond.setEndY(yS);
        lineMinute.setEndX(xM);
        lineHour.setEndX(xH);
        lineSecond.setEndX(xS);
        m.getChildren().add(lineMinute);
        m.getChildren().add(lineHour);
        m.getChildren().add(lineSecond);

    }
}
