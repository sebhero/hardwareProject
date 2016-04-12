package com.hardware.helper;

import com.hardware.service.SpringService;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by seb on 2016-04-12.
 */
@Component
public class PISerialPortEventListener implements SerialPortEventListener {

	@Autowired
	SpringService service;

	@Override
	public void serialEvent(SerialPortEvent serialPortEvent) {
		System.out.println("GOt request from Arudino");
		//service.testSendingRFID();
	}
}
