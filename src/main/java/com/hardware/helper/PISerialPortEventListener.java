package com.hardware.helper;

import com.hardware.service.RxTxService;
import com.hardware.service.SpringService;
import gnu.io.RXTXCommDriver;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by seb on 2016-04-12.
 */
@Component
public class PISerialPortEventListener implements SerialPortEventListener {

	@Autowired
	SpringService service;

	private SerialPort serialPort;
	private BufferedReader input;

	@Override
	public void serialEvent(SerialPortEvent oEvent) {
		System.out.println("GOt request from Arudino");
		//service.testSendingRFID();

		if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
			try {

				String inputLine = input.readLine();
				System.out.println("Input: " + inputLine);
			} catch (Exception e) {
				System.err.println(e.toString());
			}
		}
	}

	public void setSerialPort(SerialPort serialPort) {
		this.serialPort = serialPort;
		try {
			input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
