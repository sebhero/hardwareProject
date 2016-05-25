package com.hardware.gui;

import com.hardware.model.PiStamp;
import com.hardware.model.WelcomeReset;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;

/**
 * Created by Johnatan S.
 * This class handles the main window what is displayed on the Pi screen.
 */
@Component
public class MainLayout extends BorderPane {


	private PiStamp serverAnswer;
	GridPane gp = new GridPane();
	BorderPane bp = new BorderPane();
	Circle clock = new Circle(80, new Color(0,0,0,0));
	private Label lblWelcome = new Label("WELCOME!");
	private Text txtUser = new Text();
	private Timer timerWelcome;
	private Label lblFail = new Label();
	private Text txtDate = new Text("You checked ");

	/**
	 * This constructor sets the properties of the graphical parts.
	 */
	public MainLayout() {
		setStyle("-fx-background-color: linear-gradient(from 88% 25% to 70% 10%, #ffffffff, #3F51B5)");
		timerWelcome = new Timer();
		setPadding(new Insets(25, 25, 25, 25));
		txtUser.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		txtDate.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		lblWelcome.setFont(Font.font("Tahoma", FontWeight.BOLD, 40));
		lblFail.setFont(Font.font("Tahoma", FontWeight.BOLD, 40));
		bp.setBottom(txtUser);
		setCenter(lblWelcome);
		gp.add(clock, 0, 0);
		gp.add(bp, 1,0);
		gp.setHgap(25);
		setTop(gp);
	}


	public MainLayout getContext() {
		return this;
	}

	/**
	 * This method makes sure so that the Welcome screen is reset and displayed after every use.
	 */
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
	 * This method displays an error message on the GUI when something fails.
	 *
	 * @param failToConnect The message that is to be displayed.
     */
	public synchronized void setFailMessage(String failToConnect){
		lblFail.setText(failToConnect);
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				setCenter(lblFail);
				timerWelcome.schedule(new WelcomeReset(getContext()), 5000);
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
				Calendar cal = Calendar.getInstance();
				cal.setTimeInMillis(serverAnswer.getDate());
				String format = simpleDateFormat.format(cal.getTime());
				txtDate.setText("You checked " + (serverAnswer.isCheckIn() ? "in at: ":"out at: ") + format);
				txtUser.setText("Hello " + "\n" + serverAnswer.getFirstName() + " " + serverAnswer.getLastName());
				setCenter(txtDate);
				timerWelcome.schedule(new WelcomeReset(getContext()), 5000);
			}
		});
	}
}