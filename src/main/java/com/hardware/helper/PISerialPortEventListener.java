package com.hardware.helper;

import com.hardware.model.RfidKey;
import com.hardware.piController.PiController;
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
	private PiController controller;

	/**
	 * This method will take care of an serialportevent and then return it to the controller
	 * @param oEvent serialportevent
     */
	@Override
	public void serialEvent(SerialPortEvent oEvent) {
		System.out.println("GOt request from Arudino");
		//service.testSendingRFID();

		if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
			try {
				String inputline = input.readLine();
				//// TODO: 2016-04-13 remove after test/show!
				//inputline = inputline + inputline;
				//inputline = "01234";
				//for test this if state,check testIf class in folder test
				if(inputline.length() > 8 || inputline.length() < 7){
					controller.corruptReading();
				}else{
					RfidKey key = new RfidKey(inputline);
					controller.sendToServer(key);
					System.out.println("Input: " + key.getId());
				}
			} catch (Exception e) {
				System.err.println(e.toString());
			}
		}
	}

	/**
	 * This method will set the local serialport to a destinated one
	 * @param serialPort the port we need to use
     */
	public void setSerialPort(SerialPort serialPort) {
		this.serialPort = serialPort;
		try {
			input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method will connect the controller object to this class
	 * @param controller the controller
     */
	public void setController(PiController controller) {
		this.controller = controller;
	}
}
