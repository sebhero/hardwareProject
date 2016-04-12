package com.hardware.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Created by jonatan on 2016-04-12.
 */
public class JonteTestSWindow extends Application {

    private Scene scene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Label label1 = new Label(),
                label2 = new Label();
        Button btn = new Button("Click me!");
        label1.setText("hello");
        label2.setText("hello2");
        btn.setOnAction(event -> {
            label2.setText("btnworks");
        });

        VBox layout = new VBox();
        layout.getChildren().addAll(label1,btn,label2);
        scene = new Scene(layout, 420, 380);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public Scene getScene(){
        return this.scene;
    }
}
