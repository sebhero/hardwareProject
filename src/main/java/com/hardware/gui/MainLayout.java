package com.hardware.gui;

import com.hardware.HardwareProjectApplication;
import com.hardware.model.PiStamp;
import com.hardware.model.WelcomeReset;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;


@Component
public class MainLayout extends BorderPane {


	private PiStamp serverAnswer;
	GridPane gp = new GridPane();
	BorderPane bp = new BorderPane();
	Circle clock = new Circle(80, Color.BLUE);
	private Label lblWelcome = new Label("WELCOME!");
	private Text txtUser = new Text();
	private Timer timer;

	private Text txtDate = new Text("You checked ");

	public MainLayout() {
		setStyle("-fx-background-color: linear-gradient(from 88% 25% to 70% 10%, #ffffffff, #3F51B5)");
		timer = new Timer();
		setPadding(new Insets(25, 25, 25, 25));
		//add(label1,3,3);
		//add(label2,4,0);
		//txt.setFont(new Font(10));
		//setAlignment(Pos.CENTER);
		txtUser.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		txtDate.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		lblWelcome.setFont(Font.font("Tahoma", FontWeight.BOLD, 40));
		bp.setBottom(txtUser);
		setCenter(lblWelcome);
		gp.add(clock, 0,0);
		gp.add(bp, 1,0);
		gp.setHgap(25);
		//setCenter(txtDate);
		setTop(gp);
	}

	//llalal

	public MainLayout getContext() {
		return this;
	}

	public void resetWelcome() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				setCenter(lblWelcome);
				txtUser.setText("");
			}
		});
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
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				String format = simpleDateFormat.format(serverAnswer.getDate().getTime());
				txtDate.setText("You checked " + (serverAnswer.isCheckIn() ? "in at: ":"out at: ") + format);
				txtUser.setText("Hello " + "\n" + serverAnswer.getFirstName() + " " + serverAnswer.getLastName());
				setCenter(txtDate);
				timer.schedule(new WelcomeReset(getContext()), 5000);
			}
		});
	}
}