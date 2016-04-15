package com.hardware.service;

import com.hardware.helper.PISerialPortEventListener;
import com.hardware.piController.PiController;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Scanner;

/**
 * Created by jonatan on 2016-04-12.
 */

@Component
public class RxTxService {



    PISerialPortEventListener piSerialPortEventListener;
    private PiController ctrl;

        SerialPort serialPort;
        /**
         * The port we're normally going to use.
         */
        private static final String PORT_NAMES[] = {
                "/dev/tty.usbserial-A9007UX1", // Mac OS X
                "/dev/ttyACM0", // Raspberry Pi
                "/dev/ttyUSB0", // Linux
                "COM3", // Windows
        };
        /**
         * A BufferedReader which will be fed by a InputStreamReader
         * converting the bytes into characters
         * making the displayed results codepage independent
         */
        private BufferedReader input;
        /**
         * The output stream to the port
         */
        private OutputStream output;
        /**
         * Milliseconds to block while waiting for port open
         */
        private static final int TIME_OUT = 2000;
        /**
         * Default bits per second for COM port.
         */
        private static final int DATA_RATE = 9600;


        public void initialize() {
            // the next line is for Raspberry Pi and
            //TODO glöm ej att avmarkera när det ska till PIn
            // gets us into the while loop and was suggested here was suggested http://www.raspberrypi.org/phpBB3/viewtopic.php?f=81&t=32186
            //System.setProperty("gnu.io.rxtx.SerialPorts", "/dev/ttyACM0");

            System.out.println("Ready");
            CommPortIdentifier portId = null;
            Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();

            //First, Find an instance of serial port as set in PORT_NAMES.
            while (portEnum.hasMoreElements()) {
                CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
                for (String portName : PORT_NAMES) {
                    if (currPortId.getName().equals(portName)) {
                        portId = currPortId;
                        break;
                    }
                }
            }
            if (portId == null) {
                System.out.println("Could not find COM port.");
                return;
            }

            try {
                // open serial port, and use class name for the appName.
                serialPort = (SerialPort) portId.open(this.getClass().getName(),
                        TIME_OUT);

                // set port parameters
                serialPort.setSerialPortParams(DATA_RATE,
                        SerialPort.DATABITS_8,
                        SerialPort.STOPBITS_1,
                        SerialPort.PARITY_NONE);

                // open the streams
                output = serialPort.getOutputStream();
                System.out.println("Seiralportsend");
                piSerialPortEventListener.setSerialPort(serialPort);
	            // add event listeners
                serialPort.addEventListener(piSerialPortEventListener);
                System.out.println("Seiralportsend2");
                serialPort.notifyOnDataAvailable(true);
            } catch (Exception e) {
                System.err.println(e.toString());
            }


        }
        /**
         * This should be called when you stop using the port.
         * This will prevent port locking on platforms like Linux.
         */
        public synchronized void close() {
            if (serialPort != null) {
                serialPort.removeEventListener();
                serialPort.close();
            }

        }



    public SerialPort getSerialPort() {
        return serialPort;
    }

    public void setEventHandler(PISerialPortEventListener eventHandler) {
        this.piSerialPortEventListener = eventHandler;
    }

}

