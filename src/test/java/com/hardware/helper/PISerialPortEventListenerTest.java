package com.hardware.helper;

import com.hardware.piController.PiController;
import com.hardware.service.RxTxService;
import gnu.io.SerialPort;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;

import static org.junit.Assert.*;

/**
 * Created by jonatan on 2016-04-15.
 */
public class PISerialPortEventListenerTest {

    //need it
    private SerialPort serialPort;
    private BufferedReader input;
    private PiController controller;
    private RxTxService service;
    private PISerialPortEventListener listener;

    @Before
    public void setUp() throws Exception {
        listener.setController(controller);
        listener.setSerialPort(serialPort);
    }

    /*
    Vad ska fungera ?
     */
    @Test
    public void testSerialEvent() throws Exception {

    }
}