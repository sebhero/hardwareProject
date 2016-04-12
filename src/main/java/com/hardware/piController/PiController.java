package com.hardware.piController;

import org.springframework.stereotype.Component;

/**
 * Created by jonatan on 2016-04-12.
 */
@Component
public class PiController {

	public PiController() {
		System.out.println("stared Pi controller");
	}

	public void getDataFromArduino(String rfidKey) {
		System.out.println("Got from pi: "+rfidKey);

	}
}
