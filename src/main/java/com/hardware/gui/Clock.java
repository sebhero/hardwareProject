package com.hardware.gui;

import com.hardware.model.ClockTimer;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
    private Circle clock = new Circle(105, 105, 80);
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
        lineSecond.setStrokeWidth(1);
        lineMinute.setStrokeWidth(3);
        lineHour.setStrokeWidth(3);
        lineSecond.setStrokeLineCap(StrokeLineCap.ROUND);
        lineMinute.setStrokeLineCap(StrokeLineCap.BUTT);
        lineHour.setStrokeLineCap(StrokeLineCap.SQUARE);
        lineMinute.setStrokeLineJoin(StrokeLineJoin.BEVEL);
        lineSecond.setStrokeLineJoin(StrokeLineJoin.MITER);
        lineHour.setStrokeLineJoin(StrokeLineJoin.ROUND);

        clockImage();
    }
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
        double xM = 105 + 60 * Math.cos(((angleM - 90)*Math.PI)/180);
        double xH = 105 + 52 * Math.cos(((angleH - 90)*Math.PI)/180);
        double xS = 105 + 75 * Math.cos(((angleS - 90)*Math.PI)/180);
        double yM = 105 + 60 * Math.sin(((angleM - 90)*Math.PI)/180);
        double yH = 105 + 52 * Math.sin(((angleH - 90)*Math.PI)/180);
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
