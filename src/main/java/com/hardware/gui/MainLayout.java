package com.hardware.gui;

import com.hardware.model.PiStamp;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class MainLayout extends GridPane {


	private PiStamp serverAnswer;
	private Label label1 = new Label("Server");

	public MainLayout() {

		add(label1,3,3);
		add(new Label("Sent"), 0, 0);
		add(new Label("Received"), 1, 0);
		add(new Label("Send Form"), 0, 2);

		Button btnTest = new Button("Test");
		btnTest.setOnAction(event -> {
			System.out.println("Hello");
		});

		add(btnTest,1,2);
	}

	public void setServerAnswer(PiStamp serverAnswer) {
        System.out.println("i gui (trying to print out)");
        this.serverAnswer = serverAnswer;
		label1.setText(serverAnswer.toString());
	}
}