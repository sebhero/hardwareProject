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
import java.io.OutputStream;

/**
 * @author Johnatan S.
 * This class handles the communication between the pi and arduino.
 * It takes care of both the reading from and writing to the arduino.
 */

@Component
public class PISerialPortEventListener{

	@Autowired
	SpringService service;

	private SerialPort serialPort;
	private BufferedReader input;
	private OutputStream output;
	private PiController controller;

	/**
	 * This inner class handles the reading from arduino. When a card is scanned, the content are
	 * being processed here. Which in turn is sent to the controller.
	 *
	 */
	public class serialRead implements SerialPortEventListener {
		private BufferedReader inputS;
		private PiController controllerS;
		public serialRead(BufferedReader input, PiController controller){
			inputS = input;
			controllerS = controller;
		}

		@Override
		public void serialEvent(SerialPortEvent serialPortEvent) {
			if (serialPortEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
				try {
					String inputLine = inputS.readLine();
					System.out.println(inputLine);
					if (inputLine.length() > 8 || inputLine.length() < 7) {
						controllerS.corruptReading();
					} else {
						RfidKey key = new RfidKey(inputLine);
						controllerS.sendToServer(key);
					}
				} catch (Exception e) {
					System.err.println(e.toString());
				}
			}
		}

	}

	/**
	 * This inner class handles the writing to arduino. It sends back the status of the card.
	 */
	public class SerialWriter implements Runnable{
		OutputStream out;
		private PiController controllerS;

		public SerialWriter ( OutputStream out, PiController controller )
		{
			this.out = out;
			controllerS = controller;
		}
		public void run ()
		{
			int status;
			try
			{

				while ( true)
				{
					while((status = controllerS.status) !=-1) {
						this.out.write(status+48);
						controllerS.setStatus(-1);
					}


				}
			}
			catch ( IOException e )
			{
				e.printStackTrace();
				System.exit(-1);
			}
		}
	}

	/**
	 * This method will set the local serialport to a destinated one
	 * @param serialPort the port we need to use
     */
	public BufferedReader setSerialPort(SerialPort serialPort) {
		this.serialPort = serialPort;
		try {
			input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return input;
	}
	/**
	 * This method will connect the controller object to this class
	 * @param controller the controller
     */
	public void setController(PiController controller) {
		this.controller = controller;


	}
}
