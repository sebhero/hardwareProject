package com.hardware.gui;

import com.hardware.model.PiStamp;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;


@Component
public class MainLayout extends GridPane {


	private PiStamp serverAnswer;
	private Label label1 = new Label("Server");
	private Label label2 = new Label("datum");

	public MainLayout() {

		add(label1,3,3);
		add(label2,4,0);
		add(new Label("Sent"), 0, 0);
		add(new Label("Received"), 1, 0);
		add(new Label("Send Form"), 0, 2);

		Button btnTest = new Button("Test");
		btnTest.setOnAction(event -> {
			System.out.println("Hello");
		});

		add(btnTest,1,2);
	}

	/**
	 * Prints out the answer from the server
	 * @param serverAnswer an pistamp with information
     */
	public synchronized void setServerAnswer(PiStamp serverAnswer) {
        System.out.println("i gui (trying to print out)");
        this.serverAnswer = serverAnswer;

		//For connection too our Fx thread
		Platform.runLater(new Runnable() {
			public void run() {
				label1.setText(serverAnswer.getFirstName() + " " + serverAnswer.getLastName() + " checked " +
						(serverAnswer.isCheckIn() ? "in":"out"));
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				String format = simpleDateFormat.format(serverAnswer.getDate().getTime());
				label2.setText(format);
			}
		});
	}
}