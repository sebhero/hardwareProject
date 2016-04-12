package com.hardware.gui;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by seb on 2016-04-12.
 */
@Component
public class MainLayout extends GridPane {


	@Autowired
	public MainLayout() {

		add(new Label("Sent"), 0, 0);
		add(new Label("Received"), 1, 0);
		add(new Label("Send Form"), 0, 2);

		Button btnTest = new Button("Test");
		btnTest.setOnAction(event -> {
			System.out.println("Hello");
		});
	}
}