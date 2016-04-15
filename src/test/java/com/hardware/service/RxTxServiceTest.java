package com.hardware.service;

import com.hardware.helper.PISerialPortEventListener;
import com.hardware.piController.PiController;
import gnu.io.SerialPort;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

/**
 * Created by jonatan on 2016-04-13.
 */
public class RxTxServiceTest {

    private RxTxService input;

    @Before
    public void setUp(){
        input = new RxTxService();
        input.setEventHandler(new PISerialPortEventListener());
        input.initialize();
    }

    @Test
    public void testInitialize() throws Exception {

    }

    @Test
    public void testRun() throws Exception {

    }

    @Test
    public void testSerialEvent() throws Exception {

    }

    @Test
    public void testGetSerialPort() throws Exception {
        //Given
        //when
        Object test = input.getSerialPort();
        //then
        if(test instanceof SerialPort)
            System.out.println("true");
        else
            System.out.println("false");
    }

    @Test
    public void testSetEventHandler() throws Exception {

    }
}