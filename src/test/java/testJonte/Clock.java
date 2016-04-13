package testJonte;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by jonatan on 2016-04-12.
 */
public class Clock extends Application {
    private Timeline delayTimeline;

    @Override
    public void start(Stage primaryStage) throws Exception {
        StackPane root = new StackPane();
        root.setAlignment(Pos.CENTER);
        //add a pick for the clock
        //ImageView image = new ImageView(new Image(getClass().getResourceAsStream("clock.png")));
        //root.getChildren().addAll(image);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        Scene scene = new Scene(root,220,220, Color.TRANSPARENT);

        LinearGradient linear = new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE, new Stop[]{
                new Stop(0, Color.WHITE),
                new Stop(0.6, Color.TRANSPARENT)
        });

        final Line hour = new Line(300, 300, 300, 420);
        hour.setStrokeWidth(5);
        hour.setStroke(linear);
        final Line min = new Line(330, 330, 330, 500);
        min.setStrokeWidth(3);
        min.setStroke(linear);
        final Line sec = new Line(330, 330, 330, 500);
        sec.setStrokeWidth(1);
        sec.setStroke(linear);
        root.getChildren().addAll(hour, min, sec);

        Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        int hours = cal.getTime().getHours();
        int minut = cal.getTime().getMinutes();
        hours = hours * 30 + minut / 2;
        int second = cal.getTime().getSeconds();
        minut = minut + second / 10;
        second = second * 6;

        hour.setRotate(hours);
        min.setRotate(minut);
        sec.setRotate(second);
        
        delayTimeline = new Timeline();
        delayTimeline.getKeyFrames().add(
        new KeyFrame(new Duration(1000 - (System.currentTimeMillis() % 1000)), new EventHandler<actionevent>() {
            @Override
            public void handle(ActionEvent event) {
                Timeline secondTimeline = new Timeline();
                secondTimeline.setCycleCount(Timeline.INDEFINITE);
                secondTimeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(1), new EventHandler<actionevent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Calendar cal = Calendar.getInstance(TimeZone.getDefault());
                        int hours = cal.getTime().getHours();
                        int minut = cal.getTime().getMinutes();
                        hours = hours * 30 + minut / 2;
                        minut = minut * 6;
                        int second = cal.getTime().getSeconds();
                        minut = minut + second / 10;
                        second = second * 6;
                        hour.setRotate(hours);
                        min.setRotate(minut);
                        sec.setRotate(second);
                    }
                }));
                secondTimeline.play();
            }
        }));
        delayTimeline.play();

    }
}
