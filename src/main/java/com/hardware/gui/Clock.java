package com.hardware.gui;

import com.hardware.model.ClockTimer;
import com.hardware.service.SpringService;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Timer;

/**
 * Created by Johnatan S.
 *
 * This class creates a clock that is to be used on the display.
 */
public class Clock {
    private  Calendar calendar = Calendar.getInstance();
    private long piTime;
    private int hour;
    private int minute;
    private int second;
    private int dayCycle = 0;
    private Line lineMinute = new Line();
    private Line lineHour = new Line();
    private Line lineSecond = new Line();
    private Timer timerClock;
    private Circle clock = new Circle(105, 105, 80);
    SpringService s;
    MainLayout m;


    /**
     * This constructor sets the properties of the clock parts and
     * creates the timer.
     *
     * @param m the window which the clock is going to be displayed at.
     * @param s the server-controller that is used for clock-synchronization
     */
    public Clock(MainLayout m, SpringService s){
        this.m = m;
        this.s = s;
        syncTime();
        timerClock = new Timer();
        timerClock.scheduleAtFixedRate(new ClockTimer(this), 10, 1000);
        lineMinute.setStartX(105);
        lineMinute.setStartY(105);
        lineSecond.setStartX(105);
        lineSecond.setStartY(105);
        lineHour.setStartX(105);
        lineHour.setStartY(105);
        lineSecond.setStrokeWidth(1);
        lineMinute.setStrokeWidth(3);
        lineHour.setStrokeWidth(3);
        lineSecond.setStrokeLineCap(StrokeLineCap.ROUND);
        lineMinute.setStrokeLineCap(StrokeLineCap.BUTT);
        lineHour.setStrokeLineCap(StrokeLineCap.SQUARE);
        lineMinute.setStrokeLineJoin(StrokeLineJoin.BEVEL);
        lineSecond.setStrokeLineJoin(StrokeLineJoin.BEVEL);
        lineHour.setStrokeLineJoin(StrokeLineJoin.BEVEL);
        clockImage();
    }

    /**
     * This method creates the visual aspect of the clock.
     */
    public void clockImage(){
        ImageView imgV = new ImageView();
        Image img = new Image("clock.jpg");
        imgV.setImage(img);
        imgV.setFitWidth(160);
        imgV.setFitHeight(160);
        imgV.setPreserveRatio(true);
        imgV.setSmooth(true);
        imgV.setCache(true);
        imgV.setClip(clock);
        imgV.setX(25);
        imgV.setY(25);
        m.getChildren().add(imgV);
    }
    public void syncTime(){
        piTime = s.getPiTime();
        calendar.setTimeInMillis(piTime);
        hour = calendar.get(Calendar.HOUR);
        minute = calendar.get(Calendar.MINUTE);
        second = calendar.get(Calendar.SECOND);
    }

    /**
     * This method handles the clock movement. Updates every second with the help of a timer.
     *
     */
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
            if(dayCycle == 1){
                dayCycle=0;
                syncTime();
            }
            else {
                dayCycle++;
                hour = 0;
            }
        }
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                clockLine(hour, minute, second);
            }
        });
    }

    /**
     * This method handles the coordinates of the lines in the clock. Uses mathematical algorithms
     * to make sure that the lines will behave properly.
     *
     * @param hour the hour which the hourline is to be set at
     * @param minute the minute which the minuteline is to be set at
     * @param second the second which the secondline is to be set at
     */
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
        double xM = 105 + 60 * Math.cos(((angleM - 90)*Math.PI)/180);
        double xH = 105 + 48 * Math.cos(((angleH - 90)*Math.PI)/180);
        double xS = 105 + 75 * Math.cos(((angleS - 90)*Math.PI)/180);
        double yM = 105 + 60 * Math.sin(((angleM - 90)*Math.PI)/180);
        double yH = 105 + 48 * Math.sin(((angleH - 90)*Math.PI)/180);
        double yS = 105 + 75 * Math.sin(((angleS - 90)*Math.PI)/180);
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
